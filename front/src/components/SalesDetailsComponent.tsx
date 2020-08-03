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
import CategoryArticleCard, {
  CategoryArticleCardProps,
} from "./Category/CategoryArticleCard";
import colors from "../colors";

const ANIMATE_START_VALUE = 0.98;

export default function SalesDetailsComponent({
  title,
  price,
  createdAt,
  detail,
  additional,
  thumbnail,
}: CategoryArticleCardProps) {
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
        <CategoryArticleCard
          title={title}
          price={price}
          createdAt={createdAt}
          detail={detail}
          additional={additional}
          thumbnail={thumbnail}
        />
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
