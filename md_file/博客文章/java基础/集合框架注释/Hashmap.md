```java
/**
     * Implements Map.put and related methods.
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // table是hash数组的引用，如果为空或者长度为0，都执行重新hash操作
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            // 对应的hash表位置没有数据，就直接新建并写入
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                //如果原本的位置有值，原来位置的hash值相等，
                //并且原来位置的key值地址相等，或者新key不为，
                //且调用equals方法比较原来的key结果为true，则先将原位置的值临时保存给e变量
                //简单来说，就是原位置的key与要插入的key在hash与equals方向上是相等的
                e = p;
            else if (p instanceof TreeNode)
                //如果已经树化，则执行树化的方法
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                //若是key值并不相同，原本的位置上又有值了，
                //则会执行链表的next指向操作，并中断操作
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // next指向的元素不为null，其hash值与要插入的hash值相等
                    //并且原来位置的key值地址相等，或者新key不为，
                	//且调用equals方法比较原来的key结果为true，则中断循环
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    // 没看出这行代码的意义似乎并用不上
                    p = e;
                }
            }
            
            // key一样，则直接替换值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```



#### hash碰撞似乎并不影响数据的获取和存储？为了避免hash碰撞，似乎仅仅是为了性能的考虑?

经过测试和研究，

hash碰撞只是改变了数据的存储形式，多了链表查找的逻辑，会对性能有所影响，但并不会对具体键值对的存储和获取产生任何影响。

原来这种hash碰撞的场景是对散列表的存储结构和性能产生了影响，亏得我以前一直以为它会出现拉出一长串数据的现象。