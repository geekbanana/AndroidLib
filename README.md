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

## 2. 项目使用  
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
