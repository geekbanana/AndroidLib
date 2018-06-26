预览图：  
![img](./preview.gif)

# AndroidLib
一个Android项目通用的基础类库，项目采用 MVP + Retrofit2 + RxJava + OkHttp3 + Glide

## 1.项目模块
- **app:** 项目使用的sample

- **ptr-lib:** 上下拉刷新模块

- **library:** 核心基础类库, 包括：

  - **mvp:** 包括用于MVP架构的presenter和view, 用于Retrofit的service, 用于RxJava的subscriber

  - **net:** 网络请求的Manager, 网络状态的Receiver, 网络工具类

  - **toolbox:** 缓存，异常，imageloader, 各类manager(ActivityManager, GsonManager, HandlerManager, ThreadPoolManager), 各种常用utils(数字转换，屏幕尺寸，dp转换，加密，IO, Log，Toast, 资源文件相关，其他工具类)

  - **ui:** 常规BaseActivity和带有上下拉刷新功能的BaseActivity, 常规BaseFragment和带有上下拉刷新功能的BaseFragment, 可以设置Title的BaseAdapter

  - **view:** 自定义view, 包括MaterialProgress, 带波纹点击效果的RippleView，用于切换页面的不同显示状态（页面加载中，页面请求失败，页面数据为空）

  - **LibApplication:**

## 2.类的继承关系
以BeautyFragment为例，这个页面的功能是从网络请求美女图片，并且带有下拉刷新和上拉加载更多的功能。

Fragment
　　^
　　|
　　|
LibBaseFragment(library中)
(定义了initData,initPresenter,initView,loadData,getParams, onSuccess,onError等模版方法，以及ButterKnife注入，注意子类不要重复注入)
　　^
　　|
　　|
LibRLBaseFragment(library中)
(增加了上下拉刷新功能，可以具体到指定某个View具备上下拉刷新功能)
　　^
　　|
　　|
BaseFragment(app中)
(优化上下拉刷新功能，固定每次请求20条数据。refresh时页码自动置为0，loadmore时页码自动++。请求出现错误时，页码自动还原到正确位置)
　　^
　　|
　　|
GankioBaseFragment(app中)
（增加url拼接功能。根据项目实际情况修改）
　　^
　　|
　　|
BeautyFragment(app中)
依次执行：initData(),initPresenter(),initView(),loadData()方法

其中initPresenter中创建了一个TagPresenter，它的继承关系如下：
BasePresenter<V>
　　^
　　|
　　|
LibTagPresenter<V>
(从网络请求数据的抽象方法)
　　^
　　|
　　|
TagPresenter
(从网络请求数据的具体实现)

## 3.如何请求网络
```java
public class BeautyFragment extends GankioBaseFragment {

  @Override
  public void initPresenter() {
      mPresenter = new TagPresenter(getContext(),this);
  }

  @Override
  public void loadData() {
      mPresenter.getDataCache(
              assembleUrl(ApiManager.GANKIO_BEAUTY,PAGE_SIZE+"",++mPageNo+""),/** url **/
              TAG_BEAUTY,/** 自定义的tag,用于区分一个页面中的多个请求 **/
              new TypeReference<GankioBaseBean<List<BeautyBean>>>(){}.getType());/** 数据解析成GankioBaseBean<List<BeautyBean>>类型，并在success中得到此bean对象 **/
  }

  @Override
  public void success(Object bean, int tag) {
    //网络请求成功会调用此方法
  }

  @Override
  public void error(Throwable e, int tag) {
    //网络请求失败会调用此方法
  }
}
```
initPresenter()，loadData()，success(Object bean, int tag)，error(Throwable e, int tag)已在父类中构建的模版方法，页面启动会自动按顺序调用，用户只需在相应的方法中写入功能即可

## 4.如何指定任意View获得上下拉刷新功能，并进行一次下拉刷新和上拉加载更多
```java
public class BeautyFragment extends GankioBaseFragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      setRLMode(PtrFrameLayout.Mode.BOTH);/**开启上下拉刷新功能，Fragment中在onCreateView中调用此方法；Activity中在setContentView之前调用此方法**/
      mBaseView = inflater.inflate(R.layout.fragment_beauty, container, false);
      return mBaseView;
  }

  /**
   * 指定某个View具有上下拉刷新的功能
   */
  @Override
  protected View getRLView() {
      return rvBeauty;
  }

  /**
   *  下拉刷新时会调用此方法
   */
  @Override
  protected void onRefreshStart(PtrFrameLayout frame) {
      super.onRefreshStart(frame);
      mPresenter.getData(
              assembleUrl(ApiManager.GANKIO_BEAUTY,PAGE_SIZE+"",++mPageNo+""),
              TAG_BEAUTY,
              new TypeReference<GankioBaseBean<List<BeautyBean>>>(){}.getType());
  }

  /**
   *  上拉加载更多时，会调用此方法
   */
  @Override
  protected void onLoadMoreStart(PtrFrameLayout frame) {
      loadData();
  }
}
```


## 5.如何进行页面状态替换
```java
public class BeautyFragment extends GankioBaseFragment {

  /**
   * 在此方法中初始控制页面状态变化的控件VaryViewHelperController
   * 在AS3.0+中运行的版本有此方法， AS3.0以下版本没有此方法，在任意位置初始化VaryViewHelperController即可
   */
  @Override
  protected void initVaryViewHelperController() {
      controller = new VaryViewHelperController(rvBeauty);
  }

  @Override
  public void error(Throwable e, int tag) {
        //在error中调用showState来改变页面状态
        //这里使用的是预置页面状态，用户也可以使用自定义页面状态
        controller.showState(PageStateLayout.PageState.ERROR, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoRefresh();
            }
        }
  }

  @Override
  public void success(Object bean, int tag) {
    //在success使用restore来恢复页面状态
    controller.restore();
  }
}
```
