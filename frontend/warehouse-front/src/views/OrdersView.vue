<template>
  <div class="flex h-screen w-screen" style="height: 100%; overflow: hidden;">
    <div class="content">
      <div class="header" style="height: auto; display: inherit;">
        <div class="sidebar-header">
          <router-link to="/">
            <h2>Admin Dashboard</h2>
          </router-link>
        </div>

        <!-- Navbar -->
        <div class="navbar">
          <router-link to="/" style="display: flex; flex-direction: column;">
            <div class="nav_link">
              <v-icon name="hi-home" class="icon" />
              <h3>Home</h3>
            </div>
            <div class="line_hover"></div>
          </router-link>

          <router-link to="/orders/" style="display: flex; flex-direction: column;">
            <div class="nav_current">
              <v-icon name="bi-box-seam" class="icon" />
              <h3>Orders</h3>
            </div>
            <div class="line_current"></div>
          </router-link>
        </div>
      </div>

      <!-- Content -->
      <div class="display">
        <div class="view">
          <!-- Tabs -->
          <div class="sidebar h-full">
            <div class="tabs">
              <button
                v-for="(tab, index) in tabs"
                :key="tab.title"
                @click="setActiveTab(index)"
                :class="[
                  'tab',
                  activeTab === index ? 'bg-white text-[#0d1b2a]' : 'bg-white text-gray-500 hover:bg-blue-100 hover:text-[#0d1b2a]',
                ]"
              >
                <v-icon name="bi-circle-half" class="icon-side" :style="{ color: tab.color }" />
                <h3>{{ tab.title }}</h3>
              </button>
            </div>
          </div>

          <div class="p-4" style="width: 100%;">
            <div v-if="isLoading">
              Loading...
            </div>
            <div v-else>
              <div class="transition-all duration-300">
                <!-- Display list -->
                <div v-if="activeTab === 0">
                  <pending-orders-table :orders="orders.pending" @order-updated="getOrders" />
                </div>
                <div v-if="activeTab === 1">
                  <confirmed-orders-table :orders="orders.confirmed" @order-updated="getOrders" />
                </div>
                <div v-if="activeTab === 2">
                  <cancelled-orders-table :orders="orders.canceled" @order-updated="getOrders" />
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import PendingOrdersTable from "../components/PendingOrdersTable.vue";
import ConfirmedOrdersTable from "../components/ConfirmedOrdersTable.vue";
import CancelledOrdersTable from "../components/CancelledOrdersTable.vue";

export default {
  data() {
    return {
      activeTab: 0, // Default active tab
      tabs: [
        { title: "Pending Orders", color: "yellow" },
        { title: "Confirmed Orders", color: "green" },
        { title: "Cancelled Orders", color: "red" },
      ],
      orders: {
        pending: [],
        confirmed: [],
        canceled: [],
      }, // Holds data for each order status
    };
  },
  components: {
    PendingOrdersTable,
    ConfirmedOrdersTable,
    CancelledOrdersTable,
  },
  methods: {
    setActiveTab(index) {
      this.activeTab = index;
    },
    async getOrders() {
      this.isLoading = true;
      try {
        const response = await axios.get("http://localhost:8080/orders/");
        const allOrders = response.data;
        // Separate orders by status
        this.orders.pending = allOrders.filter(order => order.status === "PENDING");
        this.orders.confirmed = allOrders.filter(order => order.status === "CONFIRMED");
        this.orders.canceled = allOrders.filter(order => order.status === "CANCELED");
      } catch (error) {
        console.error("Error fetching orders: ", error);
      } finally {
        this.isLoading = false;
      }
    },
  },
  beforeRouteUpdate (to, from, next) {
      if (from.name === "OrderPage") {
        this.getOrders();
      } next();
    },
  mounted() {
    this.getOrders();
  },
};
</script>

<style scoped>
/* Tailwind setup */
@tailwind base;
@tailwind components;
@tailwind utilities;

.view {
  padding: 20px 30px;
  display: flex;
  flex-direction: row;
}

/* Sidebar Styles */
.tabs {
  padding-bottom: 10px;
  display: flex;
  flex-direction: column;
}

.sidebar {
  border-radius: 5px;
  height: 90%;
  min-width: 300px;
  padding: 20px;
  background-color: #fff;
  color: #0d1b2a;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -2px rgba(0, 0, 0, 0.1);
  margin: 20px 20px 0;
}



h2 {
  font-size: 25px;
}

.sidebar-header {
  background-color: #0d1b2a;
  padding: 15px 25px;
  font-size: 18px;
  font-weight: 600;
  min-width: 400px;
  display: flex;
  align-items: center;
}

.navbar {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}

.nav_current {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: white;
  text-decoration: none;
  background-color: #0d1b2a;
  width: 100%;
}

.tab_current {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #0d1b2a;
  text-decoration: none;
  background-color: #fff;
  width: 100%;
}

.line_current {
  background-color: #7e99a3;
  height: 3px;
  width: 80px;
  align-self: center;
  border-radius: 150px;
}

.line_current_tab {
  background-color: #e7e7e7;
  height: 1.5px;
  width: 90%;
  align-self: center;
  border-radius: 150px;
  margin-top: 15px;
}

.nav_link {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #fff;
  text-decoration: none;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.tab_link {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #0d1b2a;
  text-decoration: none;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.nav_link:hover {
  background-color: #0d1b2a;
  color: white;
  width: 100%;
}

.tab_link:hover {
  background-color: #fff;
  color: #0d1b2a;
  width: 90%;
}

.nav_link:hover + .line_hover {
  opacity: 1;
  transform: scaleX(1);
}

.tab_link:hover + .line_hover_tab {
  opacity: 1;
  transform: scaleX(1);
}

.line_hover {
  display: block;
  background-color: #7e99a3;
  height: 3px;
  opacity: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: opacity 0.3s, transform 0.3s;
  width: 80px;
  align-self: center;
  border-radius: 150px;
}

.line_hover_tab {
  display: block;
  background-color: #fff;
  height: 1px;
  opacity: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: opacity 0.3s, transform 0.3s;
  width: 90%;
  align-self: center;
  border-radius: 150px;
}

.icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
}

.icon-side {
  width: 22px;
  height: 24px;
  margin-right: 20px;
}

.content {
  flex: 1;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
  height: 100vh;
}

.content_wrapper {
  flex: 1;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

.header {
  background-color: #0d1b2a;
  padding: 10px 20px;
  color: white;
  flex-direction: row;
}

button {
  display: flex;
  flex-wrap: wrap-reverse;
  align-content: center;
  margin: 10px;
}
</style>
