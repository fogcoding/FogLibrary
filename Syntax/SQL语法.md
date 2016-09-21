## SQL语法
#### 特点：
* 在所有数据库的操作中是通用的，依靠基本的SQL语法可以完成绝大部分的操作，相当简便
* 语义清晰明了，易于记忆和使用
* 对大小写敏感 

####[大小写敏感的解决办法](http://www.cnblogs.com/zhuawang/archive/2013/01/15/2861566.html)

---
## 1.对数据表的操作 
#### 新建数据表

`sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PERSON"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,age INTEGER,sex TEXT)");`

#### 删除数据表
* 删除指定数据表
 
`drop table tableName`

#### 更新数据库
* 在表格里增加一列

`db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  `

* 在表格里删除一列

`ALTER TABLE TableName DROP BPLACE CASCADE`


---
## 2.对表格数据的操作
#### 增加
* 使用SQL语句操作

`"INSERT INTO PERSON VALUES(NULL,?,?,?)",new Object[]{}`

* 使用Android辅助类操作

`db.insert(String table, String nullColumnHack, ContentValues values);`

---
#### 删除

`db.delete("person", "age < ?", new String[]{"35"});`

---
#### 修改

`db.update(String table, Contentvalues values, String whereClause, String whereArgs);  `

> 修改操作的详解：

> Contentvalues是用来存放需要修改的值的内容，而whereCluase是用来帮助寻找需要修改的位置的限定条件，whereArgs是用来给出具体的限定消减的内容

> 下面是修改一个年龄为100岁的人的名字的代码：

`ContentValues cv = new ContentValues();`

`person person = new person();`

`person.name = "testing successful!!!!";`

`cv.put("name",person.name);`

`temp.update("PERSON",cv,"age = ?",new String[]{"100"});`

> 修改的内容存放在Contentvalues里面，而别的语句是用来帮助寻找具体修改位置的


* 示例代码：

`db.update("person", cv, "name = ?", new String[]{person.name});  `

---
#### 查询
* 使用SQL语句操作

`cursor = temp.rawQuery("SELECT * FROM PERSON WHERE age >= ?",new String[]{"8"});`

* 使用Android辅助类操作

`db.rawQuery(String sql, String[] selectionArgs);  `

`db.query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);`  

`db.query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);`  

`db.query(String distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit); `

* columns表示要查询的列所有名称集
* selection表示WHERE之后的条件语句，可以使用占位符，
* groupBy指定分组的列名，
* having指定分组条件，配合groupBy使用，
* orderBy指定排序的列名，
* limit指定分页参数，
* distinct可以指定“true”或“false”表示要不要过滤重复值

##### 查询会返回一个游标Cursor对象,对于数据结果的使用都是需要通过操作Cursor来实现的
* 对于Cursor的操作有以下几种方法：
* c.move(int offset); //以当前位置为参考,移动到指定行
* c.moveToFirst();    //移动到第一行  
* c.moveToLast();     //移动到最后一行 
* c.moveToPosition(int position); //移动到指定行  
* c.moveToPrevious(); //移动到前一行  
* c.moveToNext();     //移动到下一行
* c.isFirst();        //是否指向第一条  
* c.isLast();     //是否指向最后一条  
* c.isBeforeFirst();  //是否指向第一条之前 
* c.isAfterLast();    //是否指向最后一条之后  
* c.isNull(int columnIndex);  //指定列是否为空(列基数为0)  
* c.isClosed();       //游标是否已关闭
* c.getCount();       //总数据项数  
* c.getPosition();    //返回当前游标所指向的行数  
* c.getColumnIndex(String columnName);//返回某列名对应的列索引值  
* c.getString(int columnIndex);   //返回当前行指定列的值  


