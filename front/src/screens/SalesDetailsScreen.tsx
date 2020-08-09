/**
 * @author joseph415
 */

import React, { useEffect, useState } from "react";
import { FlatList } from "react-native";
import SalesDetailsComponent from "../components/SalesDetailsComponent";
import { MiniArticleCardProps } from "../types/types";
import axios from "axios";

export default function SalesDetailsScreen() {
  const [salesDetails, setSalesDetails] = useState<MiniArticleCardProps[]>();

  const getSalesDetails = async () => {
    const { data } = await axios.get("/article/tradeState", {
      params: {
        tradeState: "예약중|판매중",
      },
    });
    setSalesDetails(data);
  };

  useEffect(() => {
    getSalesDetails();
  }, []);

  return (
    <FlatList
      data={salesDetails}
      renderItem={({ item }) => (
        <SalesDetailsComponent
          id={item.id}
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
