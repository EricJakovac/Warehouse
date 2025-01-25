<template>
  <div class="flex h-screen w-screen">

      <div class="content">
      <div class="header" style="height: auto; display: inherit;">
        <div class="sidebar-header">
          <router-link to="/">
            <h2>Admin Dashboard</h2>
          </router-link>
        </div>

          <!--Navbar-->
          <div class="navbar">
              <router-link to="/" style="display: flex; flex-direction: column;">
                  <div class="nav_current">
                      <v-icon name="hi-home" class="icon" />
                      <h3>Home</h3>
                  </div>
                  <div class="line_current"></div>
              </router-link>

              <router-link to="/orders/" style="display: flex; flex-direction: column;">
                  <div class="nav_link">
                      <v-icon name="bi-box-seam" class="icon" />
                      <h3>Orders</h3>
                  </div>
                  <div class="line_hover"></div>
              </router-link>
          </div>

      </div>

      <!--Content-->
      <div class="view">
      <!--Button Add-->
          <div class="flex" style="align-self: self-end; padding: 10px 0; padding-right: 10px;">
          <button
              class="middle none center mr-3 rounded-lg bg-gradient-to-tr from-[#1b263b] to-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-500/20 transition-all hover:shadow-lg hover:shadow-gray-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
              @click="$router.push({path: '/add-new-product'})"
              data-ripple-light="true">
              Add new product
          </button>
          <button
              class="middle none center mr-3 rounded-lg border border-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-[#1b263b] transition-all hover:opacity-75 focus:ring focus:ring-gray-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
              data-ripple-dark="true">
              Secondary button
          </button>
          </div>
          
          <div class="p-4">
          <!-- Tailwind Tabs Warehouse -->
          <div class="flex space-x-4 border-b border-gray-300">
              <button
              v-for="(tab, index) in tabs"
              :key="tab.title"
              @click="setActiveTab(index)"
              :class="[
                  'flex-1 py-2 px-4 rounded-md transition-all duration-300 focus:outline-none',
                  activeTab === index
                  ? 'bg-[#0d1b2a] text-white'
                  : 'bg-white text-gray-500 hover:bg-blue-100 hover:text-[#0d1b2a]'
              ]">
              {{ tab.title }}
              </button>
          </div>
  
          <!--Tables-->
          <div class="transition-all duration-300" style="padding-top: 20px;">
              <div v-if="activeTab === 0" class="bg-white p-4 rounded-lg shadow-md border-l-4">
              <warehouse-rijeka-table />
              </div>
              <div v-if="activeTab === 1" class="bg-white p-4 rounded-lg shadow-md border-l-4">
              <warehouse-zagreb-table />
              </div>
          </div>
          </div>

      </div>

    </div> 
  </div>
</template>

<script>
import axios from "axios";
import WarehouseRijekaTable from "@/components/WarehouseRijekaTable.vue";
import WarehouseZagrebTable from "@/components/WarehouseZagrebTable.vue";
export default {
data() {
    return {
      activeTab: 0, // Default active tab
      tabs: [
        { title: "Rijeka" },
        { title: "Zagreb" }
      ],
      warehouses: [] //data will be fetched from API
    };
  },
  components: {
    WarehouseRijekaTable,
    WarehouseZagrebTable,
  },
  methods: {
    setActiveTab(index) {
      this.activeTab = index; // Update active tab index
    },
    async getWarehouses() {
      try {
        const response = await axios.get("http://localhost:8080/warehouses/");
        this.warehouses = response.data;
      } catch(error) {
        console.error("Error fetching warehouses: ", error);
      }
    }
  },
  mounted() {
    this.getWarehouses(); // Fetch warehouses on component mount
  }
};
</script>

<style scoped>
/* Tailwind setup */
@tailwind base;
@tailwind components;
@tailwind utilities;

/* Sidebar Styles */

.view {
  display: flex;
  flex-direction: column;
  padding: 20px 30px;
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

.line_current {
  background-color: #7E99A3;
  height: 3px;
  width: 80px;
  align-self: center;
  border-radius: 150px;
}

.nav_link {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: white;
  text-decoration: none;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.nav_link:hover {
  background-color: #0d1b2a;
  color: white;
  width: 100%;
}

.nav_link:hover + .line_hover {
  opacity: 1;
  transform: scaleX(1);
}

.line_hover {
  display: block;
  background-color: #7E99A3;
  height: 3px;
  opacity: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: opacity 0.3s, transform 0.3s;
  width: 80px;
  align-self: center;
  border-radius: 150px;
}


.icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
}

/* Content Styling */
.content {
  flex: 1;
  background-color: white;
  display: flex;
  flex-direction: column;
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

</style>
