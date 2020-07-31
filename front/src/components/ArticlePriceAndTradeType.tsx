/**
 * @author joseph415
 */

import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import { insertComma } from "../replacePriceWithComma";
import { ArticleDetailFavoriteProp } from "../types/types";
import axios from "axios";

export default function ArticlePriceAndTradeType({
  article_id,
}: ArticleDetailFavoriteProp) {
  const member_id = 1;
  const [price, setPrice] = useState(706000);
  const [tradeType, setTradeType] = useState("택배");
  const [location, setLocation] = useState("잠실동");

  useEffect(() => {
    axios.get("/favorite/" + member_id + "/" + article_id).then((res) => {
      setPrice(res.data.price);
      setTradeType(res.data.detail.tradeType);
      setLocation(res.data.detail.location);
    });
  }, [article_id]);

  return (
    <View style={styles.priceAndActionType}>
      <Text style={styles.price}>{insertComma(price.toString())}원</Text>
      <Text style={styles.text}>
        {tradeType === "직거래"
          ? `${tradeType} | ${location}`
          : `${tradeType} | ${tradeType}`}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  priceAndActionType: {
    justifyContent: "center",
    marginLeft: 5,
  },
  price: {
    fontSize: 20,
    fontWeight: "bold",
  },
  text: {
    fontSize: 15,
    color: "#888888",
  },
});
