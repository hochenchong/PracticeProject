import { View } from "@tarojs/components";
import { AtButton } from "taro-ui";
import Taro from "@tarojs/taro";

// import "taro-ui/dist/style/components/button.scss"; // 按需引入
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";


export default () => {
  const startTest = () => {
    Taro.navigateTo({ url: "/pages/questions/index" });
  };

  return (
    <View className="indexPage">
      <View className="at-article__h1 title">嘟嘟 MBTI 性格测试</View>
      <View className="at-article__h2 subTitle">
        只需几分钟，就能测试出你的性格
      </View>
      <AtButton type="primary" circle className="enterBtn" onClick={startTest}>
        参加测试
      </AtButton>
      <GlobalFooter></GlobalFooter>
    </View>
  );
};
