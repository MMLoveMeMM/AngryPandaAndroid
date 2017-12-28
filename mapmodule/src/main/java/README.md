A> : 准备百度地图申请APK KEY所需要的SHA1码:
<1> : 在Android studio里面的terminal中切换到C:\Users\rd0348\.android路径下,这个路径的rd0348根据对应计算机的目录不同而不同.
<2> : 然后执行 : android>keytool -list -v -keystore debug.keystore
<3> : 输入口令(默认是android) : android
<4> : 结果 : 
有效期开始日期: Tue Dec 20 08:47:33 CST 2016, 截止日期: Thu Dec 13 08:47:33 CST 2046
证书指纹:
         MD5: E2:2B:BC:9C:F5:5A:A1:56:67:99:C5:9C:17:19:78:EA
         SHA1: B7:81:6C:F6:CE:99:D7:7E:EC:E8:78:E8:CD:EA:B8:E6:F6:24:9A:98
         SHA256: 7A:7F:34:76:2F:2D:31:1D:3F:5C:FA:2D:6C:AB:93:9C:B5:91:1E:56:9D:C3:E3:CF:25:58:A6:3F:DD:56:15:67
         签名算法名称: SHA1withRSA
         版本: 1

B> : 申请号KEY以后,下载SDK包,按照http://blog.csdn.net/a739697044/article/details/25998619将包导入到工程.

