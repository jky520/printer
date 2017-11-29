# 打印jacob-1.18-x86.dll的存放位置
    1、放在jdk的安装路径下jre/bin下即可
# 需要安装打印驱动

# 项目中utils包中有处理word模板文档的工具类

# 使用jdk自带wsimport，自动生成代码
    
    1、cmd进入jdk的安装目录下的bin目录
    2、运行该命令：wsimport -keep -s E:\wsdl -p com.yinyuan.bh.print.api -verbose http://10.254.20.145/services/bhdxService?wsdl
    wsimport是jdk自带的命令，可以根据wsdl文档生成客户端中间代码，
    基于生成的代码编写客户端，可以省很多麻烦，把生成的代码拷入项目即可
    知识和案例参考：
    https://www.cnblogs.com/ywjy/p/5196064.html
    http://blog.csdn.net/aqsunkai/article/details/51711087