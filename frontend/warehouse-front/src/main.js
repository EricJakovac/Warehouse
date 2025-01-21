import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
//import store from "./store";
import "./assets/css/app.css";
import "oh-vue-icons/icons";
import { OhVueIcon, addIcons } from "oh-vue-icons";
import { HiHome } from "oh-vue-icons/icons";
import { BiBoxSeam } from "oh-vue-icons/icons";
import { BiBasket3 } from "oh-vue-icons/icons";
import { MdPaymentsOutlined } from "oh-vue-icons/icons";
import { IoAnalyticsSharp } from "oh-vue-icons/icons";
import { CoMagnifyingGlass } from "oh-vue-icons/icons";
import { RiSettings4Line } from "oh-vue-icons/icons";
import { MdModeeditoutline } from "oh-vue-icons/icons";
import { RiDeleteBin7Fill } from "oh-vue-icons/icons";
import { HiRefresh } from "oh-vue-icons/icons";



// main.js/ts
import { createBootstrap } from "bootstrap-vue-next";

// Add the necessary CSS
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-next/dist/bootstrap-vue-next.css";

const app = createApp(App);
addIcons(HiHome);
addIcons(BiBoxSeam);
addIcons(BiBasket3);
addIcons(MdPaymentsOutlined);
addIcons(IoAnalyticsSharp);
addIcons(CoMagnifyingGlass);
addIcons(RiSettings4Line);
addIcons(MdModeeditoutline);
addIcons(RiDeleteBin7Fill);
addIcons(HiRefresh);
app.component("v-icon", OhVueIcon);

app.use(createBootstrap()); // Important

app.use(router);
app.mount("#app");
