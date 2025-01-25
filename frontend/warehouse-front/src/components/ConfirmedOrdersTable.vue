<template>
  <div>
    <!--Title-->
    <div class="title">
      <h1>Confirmed Orders</h1>
    </div>
    <!-- Progress Bar Component -->
    <div class="w-full space-y-2">
      <p class="font-sans antialiased text-base text-stone-800 dark:text-white flex items-center justify-between font-semibold">
        <span>Completed</span>
        <span>100%</span>
      </p>
      <div class="w-full bg-stone-200 block rounded-full overflow-hidden h-4" style="margin-top: unset; margin-bottom: 20px;">
        <div class="h-full rounded-none animated-bar" style="width:100%; background-color: green;"></div>
      </div>
    </div>

    <div style="padding-bottom: 20px;">
      <span class="font-semibold title-font text-gray-700" style="font-size: 20px;">Total orders: {{ filteredData.length }}</span>
    </div>

    <!-- Search Input -->
    <div class="mb-3" style="padding-bottom: 20px;">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search orders" />
    </div>

    <!--Orders tab-->
    <div v-if="filteredData.length" class="orders-list overflow-y-auto" style="height: 340px;">
      <div 
        class="bg-white p-4 rounded-lg shadow-md border-l-4" 
        v-for="order in filteredData" 
        :key="order.orderId" style="margin-bottom: 30px;">
        <div class="text-gray-600 body-font overflow-hidden">
          <div class="container mx-auto">
            <div class="flex" style="display: flex; flex-wrap: nowrap; flex-direction: column;">
              <div 
                class="" 
                style="display: flex; justify-content: space-between; flex-direction: row; border-bottom: lightgray solid 2px; padding-bottom: 10px; text-align: left;">
                <span class="font-semibold title-font text-gray-700" style="font-size: 20px;">Order ID: {{ order.orderId }}</span>
                <span class="font-semibold title-font text-gray-700" style="font-size: 20px;">{{ formatDate(order.orderDate) }}</span>
              </div>
              <div class="md:flex-grow" style="padding-top: 10px; display: flex; flex-direction: row;">
                <div style="width: 100%; display: flex ;flex-direction: column;">
                <h3 class="font-semibold title-font text-gray-700" style="text-align: left; padding-bottom: 8px; font-size: 20px;">Order Items:</h3>
                <ul>
                  <li 
                    v-for="item in order.orderItems" 
                    :key="item.orderItemId"
                    style="text-align: left;">
                    {{ item.productCode }}, {{ item.productName }} - Quantity: {{ item.quantity }}
                  </li>
                </ul>
              </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-else class="text-center text-gray-500">
      No data found.
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    orders:{
      type: Array,
      required: true,
    }
  },
  data() {
    return {
      searchQuery: "", // Search input model
    };
  },
  computed: {
    filteredData() {
      // Filter data based on searchQuery
      const query = this.searchQuery.toLowerCase();
      return this.orders.filter(order => {
        const orderId = order.orderId.toString();
        const orderDate = String(order.orderDate).toLowerCase();
        const status = order.status.toLowerCase();
        return(
          orderId.includes(query) ||
          orderDate.includes(query) ||
          status.includes(query) ||
          order.orderItems.some(item =>
            item.productCode.toLowerCase().includes(query) || 
            item.productName.toLowerCase().includes(query)
          )
      );
    });
    },
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'numeric', day: 'numeric'};
      return new Date(date).toLocaleDateString(undefined, options);
    },
  },
};
</script>

<style scoped>
.table-container {
  margin-top: 20px;
  max-height: 300px;
  overflow-y: auto;
  border: 1px solid #ddd;
}

.table thead th {
  background-color: #f4f4f4;
  position: sticky;
  top: 0;
}

h1 {
  font-size: 33px;
  font-weight: 600;
  padding-bottom: unset;
  padding-top: 10px;
}

h2 {
  font-size: 25px;
}

.orders-list {
  overflow-y: auto;
  padding-right: 10px;
}
.orders-list::-webkit-scrollbar {
  width: 8px;
}
.orders-list::-webkit-scrollbar-thumb {
  background-color: #ddd;
  border-radius: 4px;
}

@keyframes fillProgress {
  0% {
    width: 0%;
  }
  100% {
    width: 100%;
  }
}
.animated-bar {
  animation: fillProgress 2s ease-in-out forwards;
}
</style>
