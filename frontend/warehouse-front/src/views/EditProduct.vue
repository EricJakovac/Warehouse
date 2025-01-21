<template>
  <div class="flex h-screen w-screen">
    <div class="content">
      <div class="header" style="height: auto; display: inherit;">
        <div class="sidebar-header">
          <h2>Admin Dashboard</h2>
        </div>

        <!-- Navbar -->
        <div class="navbar">
          <router-link to="/" style="display: flex; flex-direction: column;">
            <div class="nav_current">
              <v-icon name="hi-home" class="icon" />
              <h3>Home</h3>
            </div>
            <div class="line_current"></div>
          </router-link>

          <router-link to="/warehouses" style="display: flex; flex-direction: column;">
            <div class="nav_link">
              <v-icon name="bi-box-seam" class="icon" />
              <h3>Warehouse Management</h3>
            </div>
            <div class="line_hover"></div>
          </router-link>
        </div>
      </div>

      <!-- Content -->
      <div class="view">
        <div class="content">
          <div class="content_wrapper">

            <!--Alert Messages-->
            <div v-if="alertMessage" :class="alertClass" class="flex rounded-lg p-4 mb-4 text-sm" role="alert">
              <svg class="w-5 h-5 inline mr-3" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
              </svg>
              <div>
                <span class="font-medium">{{ alertTitle }}</span> {{ alertMessage }}
              </div>
            </div>

            <div class="title">
              <h1>Edit Product</h1>
            </div>

            <!-- Form -->
            <div class="flex items-center justify-center p-12">
              <div class="mx-auto w-full max-w-[550px]">
                <form @submit.prevent="submitProductUpdate">
                  <!-- code and name -->
                  <div class="-mx-3 flex flex-wrap">
                    <!-- product code -->
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productCode" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Product Code
                        </label>
                        <input
                          v-model="product.productCode"
                          type="text"
                          id="productCode"
                          class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                          disabled
                        />
                      </div>
                    </div>
                    <!-- product name -->
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productName" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Product Name
                        </label>
                        <input
                          v-model="product.productName"
                          type="text"
                          id="productName"
                          placeholder="Enter full product name"
                          class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>
                  </div>

                  <!-- Quantity and price -->
                  <div class="-mx-3 flex flex-wrap">
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productQuantity" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Quantity of Product
                        </label>
                        <input
                          v-model="product.productQuantity"
                          type="number"
                          id="productQuantity"
                          class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productMinQuantity" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Minimal Quantity of Product
                        </label>
                        <input
                          v-model="product.productMinQuantity"
                          type="number"
                          id="productMinQuantity"
                          class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>
                  </div>

                  <!-- Product price -->
                  <div class="mb-5">
                    <label for="productPrice" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                      Product Price
                    </label>
                    <input
                      v-model="product.productPrice"
                      type="number"
                      id="productPrice"
                      class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                    />
                  </div>

                  <!-- Dates -->
                  <div class="-mx-3 flex flex-wrap">
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productArriveDate" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Product Arrival Date
                        </label>
                        <input
                          v-model="product.productArriveDate"
                          type="datetime-local"
                          id="productArriveDate"
                          class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>
                    <div class="w-full px-3 sm:w-1/2">
                      <div class="mb-5">
                        <label for="productDepartureDate" class="mb-3 block text-base font-medium text-[#0d1b2a]">
                          Product Departure Date
                        </label>
                        <input
                          v-model="product.productDepartureDate"
                          type="datetime-local"
                          id="productDepartureDate"
                          class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>
                  </div>

                  <!-- Warehouse selection -->
                  <div class="mb-5">
                    <label class="mb-3 block text-base font-medium text-[#0d1b2a]">
                      Select Warehouse
                    </label>
                    <div class="flex flex-wrap gap-4">
                      <div v-for="warehouse in warehouses" :key="warehouse.id" class="flex items-center">
                        <input
                          type="radio"
                          :id="'warehouse-' + warehouse.id"
                          :value="warehouse.id"
                          v-model="product.selectedWarehouse"
                          class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"
                        />
                        <label :for="'warehouse-' + warehouse.id" class="ml-2 text-sm text-gray-700">
                          {{ warehouse.name }}
                        </label>
                      </div>
                    </div>
                  </div>

                  <!-- Buttons -->
                  <div>
                    <button class="middle none center mr-3 rounded-lg border border-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-[#1b263b] transition-all hover:opacity-75 focus:ring focus:ring-gray-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                      type="button" @click="$router.push('/')">
                      Cancel
                    </button>
                    <button class="middle none center mr-3 rounded-lg bg-gradient-to-tr from-[#7E99A3] to-[#7E99A3] py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-500/20 transition-all hover:shadow-lg hover:shadow-gray-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                      type="submit">
                      Update
                    </button>
                  </div>
                </form>
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

export default {
  data() {
    return {
      product: {
        productCode: "",
        productName: "",
        productQuantity: 0,
        productMinQuantity: 0,
        productPrice: 0,
        productArriveDate: "",
        productDepartureDate: "",
        selectedWarehouse: null,  // Single warehouse id, not an array
      },
      warehouses: [],
      alertMessage: "",
      alertTitle: "",
      alertType: "",
    };
  },
  async created() {
  try {
    const productCode = this.$route.params.id;
    console.log("Fetching product with code:", productCode); // Debugging log
    console.log("Fetched product: ", productResponse.data);
    console.log("Fetched warehouses: ", warehousesResponse.data);
    const [productResponse, warehousesResponse] = await Promise.all([
      axios.get(`http://localhost:8080/products/${productCode}`),
      axios.get("http://localhost:8080/warehouses/"),
    ]);
    this.product = { ...productResponse.data };
    this.warehouses = warehousesResponse.data;
  } catch (error) {
    console.error("Error details:", error.response ? error.response.data : error.message);
    if (error.response && error.response.status === 404) {
      this.showAlert("Product not found. Please check the ID.", "Error", "error");
    } else {
      this.showAlert("Failed to load data. Please try again later.", "Error", "error");
    }
  }
},

  methods: {
    async submitProductUpdate() {
      try {
        const productRequest = {
          productCode: this.product.productCode,
          productName: this.product.productName,
          productQuantity: this.product.productQuantity,
          productMinQuantity: this.product.productMinQuantity,
          productPrice: this.product.productPrice,
          productArriveDate: this.formatDateString(this.product.productArriveDate),
          productDepartureDate: this.formatDateString(this.product.productDepartureDate),
          warehouseId: this.product.selectedWarehouse,
        };

        const productId = this.$route.params.id;
        await axios.put(`http://localhost:8080/products/${productId}`, productRequest);
        this.showAlert("Product updated successfully!", "Success", "success");
        this.$router.push("/");
      } catch (error) {
        console.error("Failed to update product: ", error);
        this.showAlert("Failed to update product. Please try again.", "Error", "error");
      }
    },
    showAlert(message, title, type) {
      this.alertMessage = message;
      this.alertTitle = title;
      this.alertType = type;
    },
    formatDateString(date) {
      if (!date) return null;
      return new Date(date).toISOString().slice(0, 16);
    },
  },
  
  computed: {
    alertClass() {
      switch (this.alertType) {
        case "success":
          return "bg-green-100 text-green-700";
        case "error":
          return "bg-red-100 text-red-700";
        case "info":
          return "bg-blue-100 text-blue-700";
        case "warning":
          return "bg-yellow-100 text-yellow-700";
        default:
          return "";
      }
    },
  },

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

h1 {
  font-size: 40px;
  font-weight: 600;
  padding-bottom: unset;
  padding-top: 20px;
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
