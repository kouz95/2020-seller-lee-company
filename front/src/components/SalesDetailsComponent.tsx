/**
 * @author joseph415
 */

import React, { useRef } from "react";
import {
  Animated,
  StyleSheet,
  Text,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from "react-native";
import colors from "../colors";
import { ArticleCardProps } from "../types/types";
import ArticleCard from "./Common/ArticleCommon/ArticleCard";

const ANIMATE_START_VALUE = 0.98;

export default function SalesDetailsComponent({
  title,
  price,
  tradeType,
  location,
  createdAt,
  favoriteCount,
  chatCount,
  thumbnail,
}: ArticleCardProps) {
  const AnimateTouchableWithoutFeedback = Animated.createAnimatedComponent(
    TouchableWithoutFeedback,
  );

  const clickValue = useRef(new Animated.Value(1)).current;

  const clickArticleAnimate = () => {
    clickValue.setValue(ANIMATE_START_VALUE);

    Animated.timing(clickValue, {
      toValue: 1,
      duration: 150,
      useNativeDriver: true,
    }).start();
  };

  return (
    <AnimateTouchableWithoutFeedback
      onPress={clickArticleAnimate}
      style={{ transform: [{ scale: clickValue }] }}
    >
      <View style={styles.salesDetailsComponent}>
        <View style={{ margin: 5 }}>
          <ArticleCard
            title={title}
            price={price}
            tradeType={tradeType}
            location={location}
            createdAt={createdAt}
            favoriteCount={favoriteCount}
            chatCount={chatCount}
            thumbnail={thumbnail}
          />
        </View>
        <TouchableOpacity
          style={styles.salesCompletedContainer}
          activeOpacity={0.5}
        >
          <Text style={styles.salesCompletedText}>거래 완료</Text>
        </TouchableOpacity>
      </View>
    </AnimateTouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  salesDetailsComponent: {
    flex: 1,
  },
  salesCompletedContainer: {
    flexDirection: "row",
    backgroundColor: colors.primary,
    justifyContent: "center",
    alignItems: "center",
    padding: 8,
  },
  salesCompletedText: {
    fontSize: 15,
  },
});
