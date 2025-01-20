<template>
  <div>
    <div class="mb-3" style="display: flex;">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search Rijeka data" />
    </div>
    <div class="content_wrapper">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Min. Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Arrive Date</th>
            <th scope="col">Departure Date</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in filteredData" :key="item.code">
            <td>{{ item.code }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.quantity }}</td>
            <td>{{ item.min_quantity }}</td>
            <td>{{ item.price }}</td>
            <td>{{ item.arrive_date }}</td>
            <td>{{ item.departure_date }}</td>
            <!--Actions-->
            <button class="edit_btn" @click="editProduct(item)">
              E
            </button>
            <button class="delete_btn" @click="deleteProduct(item)">
              D
            </button>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      searchQuery: '',
      data: [], //data will be fetched from API
    };
  },
  computed: {
    filteredData() {
      return this.data.filter((item) =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
  methods: {
    async fetchWarehouseProducts() {
      try {
        const warehouseId = 1; 
        const response = await axios.get(`http://localhost:8080/products/warehouse/${warehouseId}`);
        this.data = response.data.map(product => ({
          code: product.productCode,
          name: product.productName,
          quantity: product.productQuantity,
          min_quantity: product.productMinQuantity,
          price: product.productPrice,
          arrive_date: product.productArriveDate,
          departure_date: product.productDepartureDate,
          id: product.productId // Assuming productId is available in the response
        }));
      } catch(error) {
        console.error("Error fetching products:", error);
      }
    },

    // Handle product deletion
    async deleteProduct(product) {
      try {
        // Send DELETE request to the backend with the productId
        await axios.delete(`http://localhost:8080/products/${product.id}`);
        
        // Remove the deleted product from the local data array
        this.data = this.data.filter(item => item.id !== product.id);
      } catch (error) {
        console.error("Error deleting product:", error);
      }
    },
    // Edit product
    editProduct(item) {
      this.$router.push({name: 'EditProduct', params: {id: item.id}});
    },
  },
  mounted() {
    this.fetchWarehouseProducts(); //fetch data when the component mounts
  }
};
</script>

<style>
@tailwind base;
@tailwind components;
@tailwind utilities;

.content_wrapper {
  flex: 1;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

</style>
