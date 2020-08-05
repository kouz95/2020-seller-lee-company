/**
 * @author joseph415
 */

import React from "react";
import { FlatList } from "react-native";
import { MockArticles } from "../data/ArticleMockData";
import CompletedSalesComponent from "../components/CompletedSalesComponent";

export default function SalesDetailsScreen() {
  return (
    <FlatList
      data={MockArticles}
      renderItem={({ item }) => (
        <CompletedSalesComponent
          title={item.title}
          price={item.price}
          tradeType={item.tradeType}
          location={item.location}
          createdAt={item.createdAt}
          favoriteCount={item.favoriteCount}
          chatCount={item.chatCount}
          thumbnail={item.thumbnail}
          tradeState={item.tradeState}
        />
      )}
      keyExtractor={(item, index) => `${index}`}
    />
  );
}
