import { createRouter, createWebHistory } from "vue-router";

import HomeView from "../views/HomeView";
import WarehouseView from "@/views/WarehouseView.vue";
import ProductView from "@/views/ProductView.vue";
import OrdersView from "@/views/OrdersView.vue";
import ReportsView from "@/views/ReportsView.vue";
import AnalysisView from "@/views/AnalysisView.vue";
import SettingsView from "@/views/SettingsView.vue";

const routes = [
  {
    name: "Home",
    path: "/",
    component: HomeView,
  },
  {
    name: "Warehouse",
    path: "/warehouse-management",
    component: WarehouseView,
  },
  {
    name: "Product",
    path: "/product-management",
    component: ProductView,
  },
  {
    name: "Orders",
    path: "/orders",
    component: OrdersView,
  },
  {
    name: "Reports",
    path: "/reports-and-analytics",
    component: ReportsView,
  },
  {
    name: "Analysis",
    path: "/predictive-analysis",
    component: AnalysisView,
  },
  {
    name: "Settings",
    path: "/settings",
    component: SettingsView,
  },
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
