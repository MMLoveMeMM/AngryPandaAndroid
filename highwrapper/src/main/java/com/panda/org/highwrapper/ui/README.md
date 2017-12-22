Login作为单独的模块;
登入后: Fragment反复嵌套
Activity -> Fragment -> Fragment -> ...
            Fragment -> Fragment -> ...
            Fragment -> Fragment -> ...

这里做了一个MVVM架构操作路线:
HomeActivity->UserInfoFragment (页面周期管理层)
                            -> UserVModel (逻辑处理层)
                            -> UserDatas (页面所需要的数据载体)




