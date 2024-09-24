import { View } from "@tarojs/components";
import Taro from "@tarojs/taro";
import descriptions from "../../data/mbtiDescriptions.json";
import { calculateMBTI } from "../../utils/bizUtils"; // 导入工具函数
import "./index.scss";
import {AtButton} from "taro-ui";

export default () => {
  // 获取答案
  const answerList = Taro.getStorageSync("answerList");
  if (!answerList || answerList.length < 1) {
    Taro.showToast({
      title: "答案为空",
      icon: "error",
      duration: 3000,
    });
  }
  const mbtiResult = calculateMBTI(answerList);

  return (
    <View className="resultPage">
      <View className="at-article__h1 title">您的 MBTI 性格类型是：{mbtiResult}</View>
      <View className="at-article__h2 subTitle">
        {descriptions[mbtiResult] || "未知类型"}
      </View>
      <AtButton type="primary" circle className="enterBtn" onClick={() => {
        Taro.reLaunch({
          url: "/pages/index/index",
        });
      }}>
        返回主页
      </AtButton>
    </View>
  );
};
