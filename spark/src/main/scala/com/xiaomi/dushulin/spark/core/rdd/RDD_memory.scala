package com.xiaomi.dushulin.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_memory {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDD_memory").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    //创建rdd，可以从集合、文件、Hadoop支持的数据集（HDFS、HBase）
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    rdd.saveAsTextFile("output")

    //分区数据以行为单位读取，但是会考虑偏移量（offset）
    //切片的数量取决于总字节数 / 预计的分区数
    /**
     * 分几个区？不能整除多出来的字节，占每个分区字节数的10%以上，就要多占一个分区，10%以下，塞到最后一个分区
     * 9byte / 2 => 4...1 => 3个区
     * 0 => (0, 4)
     * 1 => (4, 8)
     * 2 => (8, 12)
     *
     * 数据如何存储？
     * 1@ => 01
     * 2@ => 23
     * 3@ => 45
     * 4@ => 67
     * 5  => 8
     *
     * 0 => (0, 4) => 123
     * 1 => (4, 8) => 45
     * 2 => (8, 12) => /
     */
    val rdd1: RDD[String] = sc.textFile("input/w.txt", 2)
    rdd1.saveAsTextFile("output1")

    sc.stop()
  }

}
