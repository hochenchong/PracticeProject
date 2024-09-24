import { Image, View } from "@tarojs/components";
import { AtButton } from "taro-ui";

// import "taro-ui/dist/style/components/button.scss"; // 按需引入
import headerBg from "../../assets/headerBg.png";
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";

export default () => {
  return (
    <View className="index">
      <View className="at-article__h1">嘟嘟 MBTI 性格测试</View>
      <View className="at-article__h2">
        只需几分钟，就能测试出你的性格
      </View>
      <AtButton type="primary" circle>
        参加测试
      </AtButton>
      <Image src={headerBg}></Image>
      <GlobalFooter></GlobalFooter>
    </View>
  );
};
