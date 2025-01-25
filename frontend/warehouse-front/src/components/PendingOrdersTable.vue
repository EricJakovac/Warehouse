<template>
  <div>
    <!-- Search Input -->
    <div class="mb-3">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search data" />
    </div>

    <!-- Data Table -->
    <div class="table-container" v-if="filteredData.length">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Status</th>
            <th>Order Items</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in filteredData" :key="order.orderId">
            <td>{{ order.orderId }}</td>
            <td>{{ formatDate(order.orderDate) }}</td>
            <td>{{ order.status }}</td>
            <td>
              <ul>
                <li v-for="item in order.orderItems" :key="item.orderItemId">
                  {{ item.productCode }} - Quantity: {{ item.quantity }}
                </li>
              </ul>
            </td>
          </tr>
        </tbody>
      </table>
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
            item.productCode.toLowerCase().includes(query)
          )
      );
    });
    },
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric'};
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
</style>
