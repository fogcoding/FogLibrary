背景说明：

某项目打包时依赖了本地jar包,本地jar包均放在与src同级的lib下面，配置resource后package将其移动到target后无法打开

增加配置实现移动到target，但会导致jar包损坏

```
<resource>
    <directory>lib</directory>
    <filtering>true</filtering>
</resource>
```

解决方法：删除<filtering>true</filtering>



