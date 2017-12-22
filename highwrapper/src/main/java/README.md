<1> : 使用databinding时,多个module时,布局文件的名称绝对不允许重名;
<2> : 布局文件中的UI 构建所需库一定要导入正确,并且版本一定要互相一致(存在依赖关系时),比如:
compile 'com.android.support:support-v4:25.3.1'
compile 'com.android.support:design:25.4.0'
compile 'com.android.support:appcompat-v7:25.3.1'

也可以是旧版本的:
compile 'com.android.support:appcompat-v7:22.2.0'
compile 'com.android.support:support-v4:22.2.0'
compile 'com.android.support:design:22.2.0'
但是上面的不能够交换其中一个使用.
<3> : 当layout对应的数据发生变化时,数据变更后,databinding调用invalidateAll()方法进行更新,否则UI不会显示最新的数据.
<4> : 无论是Activity还是Fragment开发基类时,其中是否设置布局信息都不是一个好主意.


工具网站 :
<0> : 这个测试使用的聚合数据;
<1> json 转 javabean
https://www.bejson.com/json2javapojo/new/