/**
 * @author joseph415
 */

import React from "react";
import { FlatList } from "react-native";
import { MockArticles } from "../data/ArticleMockData";
import SalesDetailsComponent from "../components/SalesDetailsComponent";

export default function SalesDetailsScreen() {
  return (
    <FlatList
      data={MockArticles}
      renderItem={({ item }) => (
        <SalesDetailsComponent
          title={item.title}
          price={item.price}
          tradeType={item.tradeType}
          location={item.location}
          createdAt={item.createdAt}
          favoriteCount={item.favoriteCount}
          chatCount={item.chatCount}
          thumbnail={item.thumbnail}
        />
      )}
      keyExtractor={(item, index) => `${index}`}
    />
  );
}
