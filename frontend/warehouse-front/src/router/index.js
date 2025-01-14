import { createRouter, createWebHistory } from "vue-router";

import HomeView from "../views/HomeView";
import WarehouseView from "@/views/WarehouseView.vue";
import AddNewProduct from "@/views/AddNewProduct.vue";


const routes = [
  {
    name: "Home",
    path: "/",
    component: HomeView,
  },
  {
    name: "Warehouse",
    path: "/warehouses",
    component: WarehouseView,
  },
  {
    name: "AddNewProduct",
    path: "/add-new-product",
    component: AddNewProduct,
  }
  
];

const router = Router();
export default router;
function Router() {
  const router = new createRouter({
    history: createWebHistory(),
    routes,
  });
  return router;
}
