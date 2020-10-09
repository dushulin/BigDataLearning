package com.xiaomi.dushulin.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_File_PartitionData {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("filePartition")
    val sc: SparkContext = new SparkContext(conf)

    //Hadoop分区是以文件为单位进行划分的

    /**
     * 12 / 3 = 4
     * 1.txt => (0, 4)
     *          (4, 6)
     * 2.txt => (0, 4)
     *          (4, 6)
     */
    val rdd: RDD[String] = sc.textFile("input2", 3)
    rdd.saveAsTextFile("output")

    sc.stop()
  }

}
