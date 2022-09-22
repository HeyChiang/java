
需要定义VM Options的home的参数，避免consumer和provider在同一台机器导致的缓存异常。
```properties
-Duser.home=D:\temp\dubbo\consumer
```
