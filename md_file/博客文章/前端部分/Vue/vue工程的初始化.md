## Vue工程的初始化



#### 资料库与资源库

-- 官方网站
https://cn.vuejs.org/



#### 如何干净轻松使用

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<!-- 开发环境版本，包含了有帮助的命令行警告 -->
		<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	</head>
	<body>
		<div id="app">
			{{ message }}
		</div>
		<script type="text/javascript">
			var data = {message:'hello,vue!'};
			var app = new Vue({
				el:'#app',
				data:data
			});
		</script>
	</body>
</html>

```



#### 如何创建更科学和具体的nodeJS工程

```sql
-- 1.安装NodeJS并配置环境变量
http://nodejs.cn/
https://nodejs.org/zh-cn/

-- 2.执行npm更新并绑定到淘宝的npm镜像
npm install -g npm
-- 3.执行下面的语句后，执行cnpm与npm一致，只是做了中国区加速的工作
npm install -g cnpm --registry=http://r.cnpmjs.org

-- 4.初始化vue-cli
cnpm install -g vue-cli

-- 5.检查版本（以下命令powershell不能生效）
vue -V

-- 6.初始化vue项目（以下命令再powershell也不能生效！++ 拉黑了拉黑了!）
vue init webpack Vue-Project
-- 会提示输入各种参数和各种插件的安装选项，自己选就好！

-- 7.进入对应的目录，执行运行命令
cnmp/npm run dev

-- 若是上一步出错：可能是选项之间存在的依赖没有处理好，可以尝试重新创建项目，什么都选择要yes，会比较慢，但是测试通过，可以无错误运行

-- 8.访问页面localhost:8080,出来vue的界面就表示初始化成功！
```



#### 如何集成element-UI

```sql
-- element-UI 官网
https://element.eleme.cn/#/zh-CN

-- 安装element-UI组件
npm i element-ui -S

-- src目录下的main.js添加element-UI的引入设置
// 导入element-UI依赖
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);




```



#### vue是如何使用的

```sql
-- 通过app.vue文件设定模板，然后会被打包起来，形成新的html，所以不能再html里直接写各种控件
```



#### 需要解决的问题

```
1.页面跳转产生的参数传递问题
2.登录状态的保持
3.网络请求和数据的保存，缓存策略

```





















