import React, { useEffect, useLayoutEffect, useState } from "react";
import {
  //BackHandler,
  Button,
  Keyboard,
  StyleSheet,
  Text,
  TouchableWithoutFeedback,
  View,
} from "react-native";
import { useNavigation } from "@react-navigation/native";
import { HeaderBackButton } from "@react-navigation/stack";
import { KeyboardAwareScrollView } from "react-native-keyboard-aware-scroll-view";
import { EvilIcons } from "@expo/vector-icons";
import { useRecoilState, useSetRecoilState } from "recoil/dist";
import ArticleFormTitle from "../components/Article/ArticleFormTitle";
import ArticleFormPrice from "../components/Article/ArticleFormPrice";
import ArticleFormScreenModal from "../components/Article/ArticleFormScreenModal";
import Photo from "../components/Common/Photo/Photo";
import Tag from "../components/Common/Tag/Tag";
import {
  articleContentsState,
  articleIsEditingState,
  articleIsModifiedState,
  articleModalActivationState,
  articlePhotosState,
  articlePriceState,
  articleSelectedCategoryState,
  articleSelectedState,
  articleTitleState,
} from "../states/articleState";
import { tagsState } from "../states/TagState";
import { Article, ArticleFormScreenNavigationProp } from "../types/types";
import theme from "../colors";
import { articlesAPI } from "../api/api";
import ArticleFormCategorySelect from "../components/Article/ArticleFormCategorySelect";
import ArticleFormContents from "../components/Article/ArticleFormContents";
import { defaultArticle } from "../data/defaultArticle";

export default function ArticleFormScreen() {
  const navigation = useNavigation<ArticleFormScreenNavigationProp>();
  const [article, setArticle] = useState<Article>(defaultArticle);
  const [editingArticle, setEditingArticle] = useRecoilState(
    articleSelectedState,
  );
  const [isEditing, setIsEditing] = useRecoilState(articleIsEditingState);

  const [photos, setPhotos] = useRecoilState(articlePhotosState);
  const [title, setTitle] = useRecoilState(articleTitleState);
  const [selectedCategory, setSelectedCategory] = useRecoilState(
    articleSelectedCategoryState,
  );
  const [price, setPrice] = useRecoilState(articlePriceState);
  const [contents, setContents] = useRecoilState(articleContentsState);
  const [tags, setTags] = useRecoilState(tagsState);
  const setArticleModalState = useSetRecoilState(articleModalActivationState);
  const [originArticle, setOriginArticle] = useState<Article>();
  const setIsModified = useSetRecoilState(articleIsModifiedState);

  // const confirmToBackAction = () => {
  //   if (isDirty()) {
  //     setArticleModalState(true);
  //     return true;
  //   }
  //   resetForm();
  //   return false;
  // };
  //
  // const backHandler = BackHandler.addEventListener(
  //   "hardwareBackPress",
  //   confirmToBackAction,
  // );

  const resetForm = () => {
    setIsEditing(false);
    setForm(defaultArticle);
    setOriginArticle(defaultArticle);
  };

  const isDirty = () => {
    return (
      article.photos.length !== originArticle?.photos.length ||
      article.title !== originArticle.title ||
      article.price !== originArticle.price ||
      article.contents !== originArticle.contents ||
      article.categoryName !== originArticle.categoryName ||
      article.tags.length !== originArticle.tags.length
    );
  };

  const incompleteCriticalItems = () => {
    return (
      photos.length === 0 ||
      title === "" ||
      price === 0 ||
      contents === "" ||
      selectedCategory === ""
    );
  };

  const confirmToLeave = () => {
    if (isDirty()) {
      setArticleModalState(true);
      return;
    }
    resetAndBack();
  };

  const resetAndBack = () => {
    resetForm();
    navigation.goBack();
  };

  const dynamicStyles = StyleSheet.create({
    priceCurrencyUnit: {
      fontSize: 18,
      color: price === 0 ? "lightgrey" : "black",
    },
    createButtonContainer: {
      backgroundColor: incompleteCriticalItems() ? "grey" : theme.primary,
      flex: 3,
      justifyContent: "center",
      alignItems: "center",
      borderTopColor: "#eaeaea",
      borderTopWidth: 1,
    },
  });

  const onSubmit = async () => {
    const data = {
      title,
      price,
      category: selectedCategory,
      contents,
      tags,
      photos,
    };

    isEditing
      ? await articlesAPI.put(article.id, data).then(() => {
          setEditingArticle({
            ...editingArticle,
            title: data.title,
            price: data.price,
            categoryName: data.category,
            contents: data.contents,
            tags: data.tags,
            photos: data.photos,
          });
        })
      : await articlesAPI.post(data);
    setIsModified(true);
    resetAndBack();
  };

  const setForm = (target: Article) => {
    setArticle(target);
    setPhotos(target.photos);
    setTitle(target.title);
    setSelectedCategory(target.categoryName);
    setPrice(target.price);
    setContents(target.contents);
    setTags(target.tags);
  };

  useLayoutEffect(() => {
    navigation.setOptions({
      title: isEditing ? "수정하기" : "글쓰기",
      headerLeft: () => (
        <HeaderBackButton
          labelVisible={false}
          onPress={confirmToLeave}
          backImage={() => (
            <EvilIcons name="chevron-left" size={35} color="black" />
          )}
        />
      ),
      headerLeftContainerStyle: { paddingLeft: 10 },
    });
  });

  useEffect(() => {
    if (isEditing) {
      setForm(editingArticle);
      setOriginArticle(editingArticle);
    }
  }, [isEditing, editingArticle]);

  // useEffect(() => backHandler.remove(), []);

  return (
    <KeyboardAwareScrollView
      enableOnAndroid
      contentContainerStyle={styles.container}
    >
      <ArticleFormScreenModal resetCreateScreen={resetForm} />
      <View style={styles.contentsContainer}>
        <View style={styles.photoContainer}>
          <Photo />
        </View>
        <TouchableWithoutFeedback onPress={Keyboard.dismiss} accessible={false}>
          <View style={styles.touchableWithoutFeedbackContainer}>
            <View style={styles.titleFormContainer}>
              <ArticleFormTitle />
            </View>
            <View style={styles.selectCategoryContainer}>
              <ArticleFormCategorySelect isEditing={isEditing} />
            </View>
            <View style={styles.priceFormContainer}>
              <Text style={dynamicStyles.priceCurrencyUnit}>₩ </Text>
              <ArticleFormPrice />
            </View>
            <View style={styles.contentsFormContainer}>
              <ArticleFormContents />
            </View>
            <View style={styles.tagFormContainer}>
              <View style={styles.tagForm}>
                <Tag />
              </View>
            </View>
          </View>
        </TouchableWithoutFeedback>
      </View>
      <View style={dynamicStyles.createButtonContainer}>
        <Button
          title={"완료"}
          color={"white"}
          disabled={incompleteCriticalItems()}
          onPress={onSubmit}
        />
      </View>
    </KeyboardAwareScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
  },
  contentsContainer: {
    flex: 32.5,
    paddingHorizontal: 20,
  },
  photoContainer: {
    flex: 4,
    justifyContent: "center",
    alignItems: "center",
    marginVertical: 10,
  },
  touchableWithoutFeedbackContainer: {
    flex: 28.5,
  },
  titleFormContainer: {
    flex: 3,
    justifyContent: "center",
    alignItems: "flex-start",
    borderTopColor: "#eaeaea",
    borderTopWidth: 1,
  },
  selectCategoryContainer: {
    flex: 3,
    justifyContent: "center",
    borderTopColor: "#eaeaea",
    borderTopWidth: 1,
  },
  priceFormContainer: {
    flex: 3,
    flexDirection: "row",
    justifyContent: "flex-start",
    alignItems: "center",
    borderTopColor: "#eaeaea",
    borderTopWidth: 1,
  },
  contentsFormContainer: {
    flex: 12.5,
    borderTopColor: "#eaeaea",
    borderTopWidth: 1,
  },
  tagFormContainer: {
    borderTopColor: "#eaeaea",
    borderTopWidth: 1,
  },
  tagForm: {
    marginVertical: 15,
  },
});
