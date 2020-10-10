# spark

## spark核心概念

### Executor与Core

#### 应用程序启动参数

| 名称              | 说明                               |
| ----------------- | ---------------------------------- |
| --num-executors   | 配置Executor的数量                 |
| --executor-memory | 配置每个Executor的内存大小         |
| --executor-cores  | 配置每个Executor的虚拟CPU core数量 |

### 并行度

多少个任务在并行执行

### 有向无环图（DAG）

![image-20200909194109789](/Users/coderlin/Documents/learning/技术总结/typorePic/spark-DAG.png)

### 提交流程

![image-20200909194756513](/Users/coderlin/Documents/learning/技术总结/typorePic/spark-提交流程.png)

## spark核心编程

### RDD

> RDD（Resilient Distributed Dataset）叫做弹性分布式数据集，是Spark中最基本的数据处理模型。代码中是一个抽象类，它代表一个弹性的、不可变、可分区、里面的元素可并行计算的集合

可分区：提高消费能力，更适合并行计算

弹性：

不可变：RDD存储的计算逻辑不可改变，一旦改变，会产生新的RDD



### RDD IO



### 核心属性

- 分区列表：

  RDD数据结构中存在分区列表，用于执行任务时并行计算，是实现分布式计算的重要属性

- 分区计算函数：

  **相同的计算逻辑**应用在不同分区

- RDD之间的依赖关系：

  RDD是计算模型的封装，当需求中需要将多个计算模型进行组合时，就需要将多个RDD建立依赖关系

- 分区器（可选）：

  当数据为KV类型数据时，可以通过设定分区器自定义数据的分区

- 首选位置：

  计算数据的位置（本地化级别）



### 执行原理

从计算的角度来讲，数据处理过程中需要计算资源（内存 & CPU）和计算模型（逻辑）。执行时，需要将计算资源和计算模型进行协调和整合。

Spark框架在执行时，先申请资源，然后将应用程序的数据处理逻辑分解成一个一个的计算任务。然后将任务发到已经分配资源的计算节点上, 按照指定的计算模型进行数据计算。最后得到计算结果。

RDD是Spark框架中用于数据处理的核心模型，接下来我们看看，在Yarn环境中，RDD的工作原理:

1) 启动Yarn集群环境

![img](file:////private/var/folders/q2/p_7s67sn2q3bb_w6qdj281xr0000gn/T/com.kingsoft.wpsoffice.mac/wps-coderlin/ksohtml/wpsVu08cN.jpg) 

2) Spark通过申请资源创建调度节点和计算节点

![img](file:////private/var/folders/q2/p_7s67sn2q3bb_w6qdj281xr0000gn/T/com.kingsoft.wpsoffice.mac/wps-coderlin/ksohtml/wps40nedk.jpg) 

3) Spark框架根据需求将计算逻辑根据分区划分成不同的任务

![img](file:////private/var/folders/q2/p_7s67sn2q3bb_w6qdj281xr0000gn/T/com.kingsoft.wpsoffice.mac/wps-coderlin/ksohtml/wps1W8t8p.jpg) 

4) 调度节点将任务根据计算节点状态发送到对应的计算节点进行计算

![img](file:////private/var/folders/q2/p_7s67sn2q3bb_w6qdj281xr0000gn/T/com.kingsoft.wpsoffice.mac/wps-coderlin/ksohtml/wpsEcJH4r.jpg) 

从以上流程可以看出RDD在整个流程中主要用于将**逻辑进行封装**，并生成Task发送给Executor节点执行计算。



### 基础编程

#### RDD创建

- 从集合（内存）中创建
- 从外部存储（文件）中创建
- 从其他RDD创建
- 直接创建RDD（new）















































