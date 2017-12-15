# 打印jacob-1.18-x86.dll的存放位置
    1、放在jdk的安装路径下jre/bin下即可
# 需要安装打印驱动
    在我的百度网盘里有

# 项目中utils包中有处理word模板文档的工具类

# 使用jdk自带wsimpor自动生成webservice的客户端代码
    
    1、cmd进入jdk的安装目录下的bin目录
    2、运行该命令：wsimport -keep -s E:\wsdl -p com.yinyuan.bh.print.api -verbose http://10.254.20.145/services/bhdxService?wsdl
    wsimport是jdk自带的命令，可以根据wsdl文档生成客户端中间代码，
    基于生成的代码编写客户端，可以省很多麻烦，把生成的代码拷入项目即可
    注意：这个测试必须要打开VPN
    知识和案例参考：
    https://www.cnblogs.com/ywjy/p/5196064.html
    http://blog.csdn.net/aqsunkai/article/details/51711087

# 多台CentOS服务器时间同步（NTP时间同步）
    一、选定其中一台服务器作为基准服务器(202.112.128.33)，即提供时钟服务。
    (1)、首先确定ntp是否存在，若不存在则通过以下命令安装
    yum install ntp 
    (2)、修改配置文件/etc/ntp.conf，在其中插入
    restrict 202.112.128.33 mask 255.255.255.0 nomodify notrap
    
    其中：
    用restrict控管权限
    nomodify - 用户端不能更改ntp服务器的时间参数
    noquery - 用户端不能使用ntpq，ntpc等命令来查询ntp服务器
    notrap - 不提供trap远端登陆
    (3)、此时可以向202.112.128.33/24的机器提供时间服务了，开启ntp服务
    /etc/rc.d/init.d/ntpd start  // 启动ntp服务  
    chkconfig ntpd on // 让ntp服务开机启动
    (4)、检查ntp端口是否已经开启：
    netstat -unlnp
    如果正常，应该可以看到123端口已经开启。
    
# spring boot jar
    可以使用以下命令编译：
    mvn clean package
    可以追加参数 -Dmaven.test.skip=true 跳过测试。 
    
    