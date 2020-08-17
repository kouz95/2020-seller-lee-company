import React from "react";
import { StyleSheet, View } from "react-native";
import { ArticleCardProps } from "../../../types/types";
import ArticleCardImage from "./ArticleCardImage";
import ArticleCardTitle from "./ArticleCardTitle";
import ArticleCardTradeDetails from "./ArticleCardTradeDetails";
import ArticleCardAdditional from "./ArticleCardAdditional";

export default function ArticleCard({
  title,
  price,
  createdTime,
  favoriteCount,
  chatCount,
  thumbnail,
  tradeState,
}: ArticleCardProps) {
  return (
    <View style={styles.container}>
      <ArticleCardImage thumbnail={thumbnail} />
      <View style={styles.contentsContainer}>
        <ArticleCardTitle title={title} />
        <ArticleCardTradeDetails createdTime={createdTime} />
        <ArticleCardAdditional
          price={price}
          chatCount={chatCount}
          favoriteCount={favoriteCount}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    aspectRatio: 13 / 4,
  },
  contentsContainer: {
    flex: 10.5,
    margin: 13,
  },
});
