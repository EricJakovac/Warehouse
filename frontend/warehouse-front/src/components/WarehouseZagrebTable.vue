<template>
  <div>
    <!--Search Input-->
    <div class="mb-3" style="display: flex;">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search Zagreb data" />
    </div>
    <!--Display Alerts-->
    <div v-if="alert.visible" :class="alertClasses" class="flex rounded-lg p-4 mb-4 text-sm" role="alert">
      <svg class="w-5 h-5 inline mr-3" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
        <path v-if="alert.type === 'success'" fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm-1-4v-3a1 1 0 112 0v3a1 1 0 11-2 0zm0-4V7a1 1 0 112 0v3a1 1 0 11-2 0z" clip-rule="evenodd"></path>
        <path v-if="alert.type === 'danger'" fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
      </svg>
      <div>
        <span class="font-medium">{{ alert.message }}</span>
      </div>
    </div>
  
    <!--Content-->
    <div class="content_wrapper">
      <div class="table-container">
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
              <td>{{ formatPrice(item.price) }}</td>
              <td>{{ formatDate(item.arrive_date) }}</td>
              <td>{{ formatDate(item.departure_date) }}</td>
              <!--Actions-->
              <button class="edit_btn" @click="editProduct(item)" style="width: 50%;">
                <div style="border-radius: 100px; background-color: #1b263b; color: #ffffff; width: 24px;">
                <v-icon name="md-modeeditoutline" class="icon" style="width: 15px;"/>
                </div>
              </button>
              <button class="delete_btn" @click="deleteProduct(item)" style="width: 50%;">
                <div style="border-radius: 100px; background-color: #1b263b; color: #ffffff; width: 24px;">
                  <v-icon name="ri-delete-bin-7-fill" class="icon" style="width: 13px;"/>
                </div>
              </button>
            </tr>
          </tbody>
        </table>
      </div>
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
      alert: {
        visible: false,
        type: '',
        message: '',
      }
    };
  },
  computed: {
    filteredData() {
      const query = this.searchQuery.toLowerCase();
      return this.data.filter((item) =>
        item.name.toLowerCase().includes(query) ||
        item.code.toLowerCase().includes(query)
      );
    },
    alertClasses() {
      return {
        'bg-green-100 text-green-700': this.alert.type === 'success',
        'bg-red-100 text-red-700': this.alert.type === 'danger',
        'bg-yellow-100 text-yellow-700': this.alert.type === 'warning',
        'bg-blue-100 text-blue-700': this.alert.type === 'info'
      }
    }
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

    formatPrice(price) {
      return`${parseFloat(price).toFixed(2)} €`;
    },

    formatDate(date) {
      if(!date) return '';
      const [year, month, day] = date.split('-');
      return `${day}/${month}/${year}`;
    },  

    // Handle product deletion
    async deleteProduct(product) {
      try {
        // Send DELETE request to the backend with the productId
        await axios.delete(`http://localhost:8080/products/${product.code}`);
        
        // Remove the deleted product from the local data array
        this.data = this.data.filter(item => item.code !== product.code);

        this.showAlert('success', 'Product deleted successfully!');
      } catch (error) {
        console.error("Error deleting product:", error);
        this.showAlert('danger', 'Failed to delete product.');
      }
    },

    showAlert(type, message) {
      this.alert.type = type;
      this.alert.message = message;
      this.alert.visible = true;

      setTimeout(() => {
        this.alert.visible = false;
      }, 4000); // Hide after 4 secs
    },

    // Edit product
    editProduct(product) {
      this.$router.push({
        name: 'EditProduct', 
        params: {id: product.code} //pass product code as id
      });
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
  border-radius: 0.375rem;
}

.table-container {
  max-height: 280px;
  overflow-y: auto;
  border: 1px solid #ddd;
}

.table thead th {
  position: sticky;
  top: 0;
  background-color: #1b263b;
  color: #fff;
  z-index: 1;
}

</style>
