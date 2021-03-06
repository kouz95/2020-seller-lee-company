import { atom } from "recoil/dist";

export const tagIdState = atom({
  key: "tagIdState",
  default: 0,
});

export const isModalOpenState = atom({
  key: "IsModalOpenState",
  default: false,
});

export const inputState = atom({
  key: "inputState",
  default: "",
});

export const tagsState = atom({
  key: "tagsState",
  default: <string[]>[],
});
