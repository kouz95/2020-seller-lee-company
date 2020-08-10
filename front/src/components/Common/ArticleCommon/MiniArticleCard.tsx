/**
 * @author joseph415
 */

import React from "react";
import { StyleSheet, View } from "react-native";
import { MiniArticleCardProps } from "../../../types/types";
import ArticleCardImage from "./ArticleCardImage";
import ArticleCardTitle from "./ArticleCardTitle";
import ArticleCardTradeDetails from "./ArticleCardTradeDetails";
import ArticleCardAdditional from "./ArticleCardAdditional";

export default function MiniArticleCard({
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
  return (
    <View key={id} style={styles.container}>
      <ArticleCardImage thumbnail={thumbnail} />
      <View style={styles.contentsContainer}>
        <ArticleCardTitle title={title} />
        <ArticleCardTradeDetails
          location={location}
          tradeType={tradeType}
          createdAt={createdTime}
        />
        <ArticleCardAdditional
          price={price}
          chatCount={chatCount}
          favoriteCount={favoriteCount}
        />
      </View>
    </View>
  );
}

export const innerContainerMargin = 13;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    aspectRatio: 13 / 4,
  },
  contentsContainer: {
    flex: 10.5,
    margin: innerContainerMargin,
  },
});
