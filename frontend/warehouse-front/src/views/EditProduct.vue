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
            <div class="nav_link">
              <v-icon name="hi-home" class="icon" />
              <h3>Home</h3>
            </div>
            <div class="line_hover"></div>
          </router-link>

          <router-link to="/orders" style="display: flex; flex-direction: column;">
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

            <!--Title-->
            <div class="title">
              <h1>Add product {{ product.productCode}},  {{ originalProductName }}</h1>
            </div>
            
            <!--Form-->
            <div class="flex items-center justify-center p-12" style="padding-top: 2rem; padding-bottom: unset;">
              <div class="mx-auto w-full" style="margin: unset !important; display: flex; justify-content: center;">
                <form @submit.prevent="updateProduct">

                  <!--1st row-->
                  <div style="display: flex; flex-direction: row; margin-bottom: 50px;">
                    <!--code and name-->
                    <div class="-mx-3 flex flex-wrap" style="width: auto; margin-right: 10px;">
                      <!--product code-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productCode" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Product code
                          </label>
                          <div style="display: flex;">
                            <input
                            v-model="product.productCode"
                            type="text"
                            id="productCode"
                            disabled
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                            style="border-top-right-radius: unset; border-bottom-right-radius: unset;"
                          />
                            <button type="button" 
                            @click="generateProductCode"
                            disabled
                            class="ml-3 px-4 py-2 text-white bg-blue-500 rounded-md text-sm" 
                            style="background-color: #1b263b; margin-left: unset; border-top-left-radius: unset; border-bottom-left-radius: unset;">
                            <v-icon name="hi-refresh" class="icon" style="margin: unset !important;" />
                          </button>
                          </div>
                        </div>
                      </div>
                      <!--product name-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productName" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Product name
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

                    <!-- quantity-->
                    <div class="-mx-3 flex flex-wrap">
                      <!--product quantity-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productQuantity" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Quantity Of Product
                          </label>
                          <input
                            v-model="product.productQuantity"
                            type="number"
                            id="productQuantity"
                            placeholder="5"
                            min="0"
                            class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                          />
                        </div>
                      </div>
                      <!--product min quantity-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productMinQuantity" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Minimal Quantity Of Product
                          </label>
                          <input
                            v-model="product.productMinQuantity"
                            type="number"
                            id="productMinQuantity"
                            placeholder="0"
                            min="0"
                            class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                          />
                        </div>
                      </div>
                    </div>

                  </div>

                  <!--2nd row-->
                  <div style="display: flex; flex-direction: row; justify-content: center; margin-bottom: 50px;">
                    <!-- product price -->
                    <div class="-mx-3 flex flex-wrap">
                      <div class="mb-5" style="padding: unset !important; margin-bottom: unset !important;">
                        <label for="productPrice" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                        style="font-weight: 600;">
                          Product Price
                        </label>
                        <input
                          v-model="product.productPrice"
                          type="number"
                          id="productPrice"
                          placeholder="0.00"
                          step="0.01"
                          class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        />
                      </div>
                    </div>

                    <!-- dates -->
                    <div class="-mx-3 flex flex-wrap" style="padding-left: 44px;">
                      <!--arrive date-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productArriveDate" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Product arrive Date
                          </label>
                          <input
                            v-model="product.productArriveDate"
                            type="date"
                            id="productArriveDate"
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#0d1b2a] focus:shadow-md"
                          />
                        </div>
                      </div>

                      <!--departure date-->
                      <div class="w-full px-3 sm:w-1/2" style="padding: unset !important;">
                        <div class="mb-5" style="margin-right: 20px; margin-bottom: unset !important;">
                          <label for="productDepartureDate" class="mb-3 block text-base font-medium text-[#0d1b2a]"
                          style="font-weight: 600;">
                            Product Departure Date
                          </label>
                          <input
                            v-model="product.productDepartureDate"
                            type="date"
                            id="productDepartureDate"
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#0d1b2a] focus:shadow-md"
                          />
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- 3rd row -->
                  <div class="mb-5" style="margin-bottom: 50px !important;">
                    <label class="mb-3 block text-base font-medium text-[#0d1b2a]"
                    style="font-weight: 600;  margin-bottom: 50px;">
                      Select Warehouse {{ product.selectedWarehouse }}
                    </label>
                    <div class="flex flex-wrap gap-4" style="display: flex; justify-content: center;">
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

                  <!-- buttons -->
                  <div>
                    <button class="middle none center mr-3 rounded-lg border border-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-[#1b263b] transition-all hover:opacity-75 focus:ring focus:ring-gray-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                      type="button" @click="cancelEdit">
                      Cancel
                    </button>
                    <button class="middle none center mr-3 rounded-lg bg-gradient-to-tr from-[#1b263b] to-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-500/20 transition-all hover:shadow-lg hover:shadow-gray-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none" 
                      type="submit">
                      Save
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
        productCode: '',
        productName: '',
        productQuantity: 0,
        productMinQuantity: 0,
        productPrice: 0,
        productArriveDate: '',
        productDepartureDate: '',
        selectedWarehouse: null,
        warehouseId: null,
      },
      originalProductName: '',
        warehouses: [
          { id: 1, name: 'Warehouse Rijeka' },
          { id: 2, name: 'Warehouse Zagreb' },
          ],
        loading: true,
        alertMessage: "",
        alertTitle: "",
        alertType: "",
    };
  },
  methods: {
    async fetchProductDetails() {
      const productCode = this.$route.params.id;
      try {
        const response = await axios.get(`http://localhost:8080/products/${productCode}`);
        this.product = response.data; // Directly map response data to product
        this.originalProductName = this.product.productName; //Store original name
        this.product.selectedWarehouse = this.product.warehouseId || null;
        } catch (error) {
        console.error("Error fetching product:", error);
      } finally {
        this.loading = false;
      }
    },
    async updateProduct() {
      const productCode = this.$route.params.id;

       // Validate the product price
      const pricePattern = /^\d+(\.\d{1,2})?$/; // Matches numbers like 12 or 12.09
      if (!pricePattern.test(this.product.productPrice) || this.product.productPrice < 0) {
        this.setAlert(
          "Product price is invalid. Please ensure it's a positive number in the format 12 or 12.09.",
          "danger",
          "Invalid Input"
        );
        return; // Prevent the update
      }

      try {
        await axios.put(`http://localhost:8080/products/${productCode}`, {
        ...this.product,
        warehouseId: this.product.selectedWarehouse || 0,
          });
        this.setAlert("Product updated successfully", "success", "Success!");


      //Redirect after 2 secs
      setTimeout(() => {
          this.$router.push('/');
        }, 2000);
      } catch(error) {
        console.error('Error adding product: ', error);
        this.setAlert('Failed to add product.', 'danger', 'Error!');
      } 
    },
    cancelEdit() {
    this.$router.push('/');
    this.resetProductForm();
    },
    resetProductForm() {
      this.product = {
        productCode: '',
        productName: '',
        productQuantity: 0,
        productMinQuantity: 0,
        productPrice: 0,
        productArriveDate: '',
        productDepartureDate: '',
        selectedWarehouse: null,
      };
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

    generateProductCode() {
    // Generate a random 6-digit product code
    this.product.productCode = Math.floor(100000 + Math.random() * 900000).toString();
  },

  },
  mounted() {
    this.fetchProductDetails();
  },

  computed: {
    alertClass() {
      return {
        'bg-blue-100 text-blue-700': this.alertType === 'info',
        'bg-red-100 text-red-700': this.alertType === 'danger',
        'bg-green-100 text-green-700': this.alertType === 'success',
        'bg-yellow-100 text-yellow-700': this.alertType === 'warning',
      };
    }
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
  font-size: 33px;
  font-weight: 600;
  padding-bottom: unset;
  padding-top: 10px;
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
