import { useState } from "react";
import { View } from "@tarojs/components";
import Taro from "@tarojs/taro";
import "./index.scss";
import questions from "../../data/question.json";
import { AtButton, AtProgress, AtRadio } from "taro-ui";

export default () => {
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [answers, setAnswers] = useState<string[]>(
    Array(questions.length).fill("")
  ); // 初始化答案数组

  // 底部进度条显示
  const progress = (currentQuestion / questions.length) * 100;

  return (
    <View className="questionPage">
      <View className="at-article__h1 title">
        {questions[currentQuestion].id}. {questions[currentQuestion].question}
      </View>
      <View className="options-wrapper">
        <AtRadio
          options={questions[currentQuestion].options.map((option) => {
            return { label: option.text, value: option.value };
          })}
          value={answers[currentQuestion]}
          onClick={(value) => {
            const newAnswers = [...answers];
            newAnswers[currentQuestion] = value; // 更新答案
            setAnswers(newAnswers); // 更新状态
            if (currentQuestion < questions.length - 1) {
              setCurrentQuestion(currentQuestion + 1);
            }
          }}
        />
      </View>
      {currentQuestion === questions.length - 1 && (
        <AtButton
          type="primary"
          circle
          className="controlBtn"
          disabled={!answers[currentQuestion]}
          onClick={() => {
            // 保存结果
            Taro.setStorageSync("answerList", answers);
            // 查看结果
            Taro.navigateTo({
              url: `/pages/result/index`,
            });
          }}
        >
          查看结果
        </AtButton>
      )}
      {currentQuestion > 0 && (
        <AtButton
          circle
          className="controlBtn"
          onClick={() => setCurrentQuestion(currentQuestion - 1)}
        >
          上一题
        </AtButton>
      )}
      <AtProgress percent={progress} status="progress" className="progress" />
    </View>
  );
};
