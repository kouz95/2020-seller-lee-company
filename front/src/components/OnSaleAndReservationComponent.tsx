/**
 * @author joseph415
 */

import React, { useRef, useState } from "react";
import {
  Animated,
  StyleSheet,
  Text,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from "react-native";
import colors from "../colors";
import { MiniArticleCardProps, MyPageParamList } from "../types/types";
import { useNavigation } from "@react-navigation/native";
import { StackNavigationProp } from "@react-navigation/stack";
import MiniArticleCard from "./Common/ArticleCommon/MiniArticleCard";
import { articlesAPI } from "../api/api";

const ANIMATE_START_VALUE = 0.93;

type SalesDetails = StackNavigationProp<MyPageParamList, "SalesDetails">;

export default function OnSaleAndReservationComponent({
  id,
  title,
  price,
  tradeType,
  location,
  createdTime,
  favoriteCount,
  chatCount,
  thumbnail,
  tradeState,
}: MiniArticleCardProps) {
  const navigation = useNavigation<SalesDetails>();
  const AnimateTouchableWithoutFeedback = Animated.createAnimatedComponent(
    TouchableWithoutFeedback,
  );

  const [tradeStateState, setTradeStateState] = useState(tradeState);

  const clickValue = useRef(new Animated.Value(1)).current;

  const clickArticleAnimate = () => {
    clickValue.setValue(ANIMATE_START_VALUE);

    Animated.timing(clickValue, {
      toValue: 1,
      duration: 150,
      useNativeDriver: true,
    }).start();
    navigation.navigate("ArticleDetailScreen");
  };

  const patchTradeState = async (data: string) => {
    await articlesAPI.patch({ id: id, tradeState: data });
    setTradeStateState(data);
  };

  return (
    <AnimateTouchableWithoutFeedback
      onPress={clickArticleAnimate}
      style={{ transform: [{ scale: clickValue }] }}
    >
      <View style={styles.salesDetailsComponent}>
        <View style={styles.miniArticleContainer}>
          <MiniArticleCard
            id={id}
            title={title}
            price={price}
            tradeType={tradeType}
            location={location}
            createdTime={createdTime}
            favoriteCount={favoriteCount}
            chatCount={chatCount}
            thumbnail={thumbnail}
            tradeState={tradeStateState}
          />
        </View>
        {tradeState === "판매 완료" ? (
          <TouchableOpacity
            style={styles.salesCompletedContainer}
            activeOpacity={0.5}
            onPress={() => navigation.navigate("EvaluationBuyer")}
          >
            <Text style={styles.salesCompletedText}>구매자 평가</Text>
          </TouchableOpacity>
        ) : (
          <View style={styles.onSaleAndReservationContainer}>
            <TouchableOpacity
              style={styles.reservationContainer}
              activeOpacity={0.5}
              onPress={() => patchTradeState("예약중")}
            >
              <Text style={styles.salesCompletedText}>예약중</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={styles.onSaleContainer}
              activeOpacity={0.5}
              onPress={() => patchTradeState("판매 완료")}
            >
              <Text style={styles.salesCompletedText}>판매 완료</Text>
            </TouchableOpacity>
          </View>
        )}
      </View>
    </AnimateTouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  salesDetailsComponent: {
    flex: 1,
  },
  miniArticleContainer: { margin: 5 },
  salesCompletedContainer: {
    backgroundColor: colors.primary,
    justifyContent: "center",
    alignItems: "center",
    padding: 8,
  },
  salesCompletedText: {
    fontSize: 15,
  },
  onSaleAndReservationContainer: {
    flex: 1,
    flexDirection: "row",
    backgroundColor: colors.primary,
  },
  reservationContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 8,
    borderRightWidth: 0.6,
  },
  onSaleContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 8,
  },
});
