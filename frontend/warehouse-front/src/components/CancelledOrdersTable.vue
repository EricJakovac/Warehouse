<template>
  <div>
    <!--Title-->
    <div class="title">
      <h1>Cancelled Orders</h1>
    </div>
    <!-- Progress Bar Component -->
    <div class="w-full space-y-2">
      <p class="font-sans antialiased text-base text-stone-800 dark:text-white flex items-center justify-between font-semibold">
        <span>Completed</span>
        <span>10%</span>
      </p>
      <div class="w-full bg-stone-200 block rounded-full overflow-hidden h-4" style="margin-top: unset; margin-bottom: 20px;">
        <div class="h-full rounded-none animated-bar" style="width:10%; background-color: red;"></div>
      </div>
    </div>

    <div style="padding-bottom: 20px;">
      <span class="font-semibold title-font text-gray-700" style="font-size: 20px;">Total orders: {{ filteredData.length }}</span>
    </div>

    <!--Alert Messages-->
    <div v-if="alertMessage" :class="alertClass" class="flex rounded-lg p-4 mb-4 text-sm" role="alert">
      <svg class="w-5 h-5 inline mr-3" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
      </svg>
      <div>
        <span class="font-medium">{{ alertTitle }}</span> {{ alertMessage }}
      </div>
    </div>

    <!-- Search Input -->
    <div class="mb-3" style="padding-bottom: 20px;">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search orders" />
    </div>

    <!--Orders tab-->
    <div v-if="filteredData.length" class="orders-list overflow-y-auto" style="height: 290px;">
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
                <div style="display:  flex; flex-direction: column; justify-content: end;">
                  <span class="font-semibold title-font text-gray-700" style="font-size: 15px; display: flex; justify-content: end;">{{ formatDate(order.orderDate) }}</span>
                  <span class="font-semibold title-font text-gray-700" style="font-size: 15px;">
                    <span v-if="order.warehouseId === 1"> Warehouse Rijeka</span>
                    <span v-else-if="order.warehouseId === 2"> Warehouse Zagreb</span>
                    <span v-else> Unknown Warehouse</span>
                  </span>
                </div>
              </div>
              <div class="md:flex-grow" style="padding-top: 10px; display: flex; flex-direction: row;">
                <div style="width: 70%; display: flex ;flex-direction: column;">
                <h3 class="font-semibold title-font text-gray-700" style="text-align: left; padding-bottom: 8px; font-size: 20px;">Order Items:</h3>
                <ul>
                  <li 
                    v-for="item in order.orderItems" 
                    :key="item.orderItemId"
                    style="text-align: left;">
                    {{ item.productCode }}, {{ item.productName }} <br><strong>Quantity:</strong> {{ item.quantity }}
                  </li>
                </ul>
              </div>
              <div style="width: 30%; display: flex; flex-direction: row; justify-content: end;">
                <button
                    @click="updateOrderStatus(order)"
                    class="middle none center mr-3 rounded-lg bg-gradient-to-tr from-[#1b263b] to-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-500/20 transition-all hover:shadow-lg hover:shadow-gray-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    data-ripple-light="true"
                    style="height: 50px; margin-right: 0px;">
                    Repeat order
                </button>
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
      alertMessage: "",
      alertTitle: "",
      alertType: "",
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
            item.productName.toLowerCase().includes(query) ||
            item.warehouseId
          )
      );
    });
    },
    alertClass() {
      return {
        'bg-blue-100 text-blue-700': this.alertType === 'info',
        'bg-red-100 text-red-700': this.alertType === 'danger',
        'bg-green-100 text-green-700': this.alertType === 'success',
        'bg-yellow-100 text-yellow-700': this.alertType === 'warning',
      };
    }
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'numeric', day: 'numeric'};
      return new Date(date).toLocaleDateString(undefined, options);
    },
    async updateOrderStatus(order) {
  try {
    // Trigger the reorder endpoint
    const endpoint = `/orders/${order.orderId}/reorder`;
    const response = await axios.put(endpoint);
    
    // If the response is successful, update the order status
    if (response.status === 200) {
      order.status = "PENDING"; // Set the status to "PENDING"
      this.setAlert ("Order has been successfully reordered!",  "success", "Success!");
       // Remove the order from the list
      const index = this.orders.findIndex((o) => o.orderId === order.orderId);
      if (index !== -1) {
        this.orders.splice(index, 1); // Remove the order from the list
      }
    }
  } catch (error) {
    console.error("Error repeating order: ", error);
    this.setAlert("There was an issue reordering the order. Please check the order status and try again.", "danger", "Error!")
  }
},
setAlert(message, type, title) {
      this.alertMessage = message;
      this.alertType = type;
      this.alertTitle = title;

       // Auto clear the alert after 5 seconds
    setTimeout(() => {
      this.alertMessage = "";
      this.alertTitle = "";
      this.alertType = "";
    }, 5000);
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
    width: 10%;
  }
}
.animated-bar {
  animation: fillProgress 2s ease-in-out forwards;
}
</style>
