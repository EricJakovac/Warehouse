<template>
  <div class="flex h-screen w-screen">
    <div class="content">
      <div class="header" style="height: auto; display: inherit;">
        <div class="sidebar-header">
          <h2>Admin Dashboard</h2>
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
     y
    </div> 
  </div>
</template>

<script>
import axios from 'axios';

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
        selectedWarehouse: null,  // Single warehouse id, not an array
      },
      warehouses: [
        { id: 1, name: 'Warehouse Rijeka' },
        { id: 2, name: 'Warehouse Zagreb' },
      ],
      alertMessage: "",
      alertTitle: "",
      alertType: "",
    };
  },

  methods: {
    generateProductCode() {
    // Generate a random 6-digit product code
    this.product.productCode = Math.floor(100000 + Math.random() * 900000).toString();
  },
    async submitProduct() {
      try {
        if(isNaN(this.product.productPrice) || parseFloat(this.product.productPrice)<0) {
          this.setAlert('Please input a valid price, e.g., 12 or 12.01', 'warning', 'Warning!');
          return;
        }

        const productRequest = {
          productCode: this.product.productCode,
          productName: this.product.productName,
          productQuantity: this.product.productQuantity,
          productMinQuantity: this.product.productMinQuantity,
          productPrice: parseFloat(this.product.productPrice).toFixed(2),
          productArriveDate: this.product.productArriveDate,
          productDepartureDate:this.product.productDepartureDate,
          warehouseId: this.product.selectedWarehouse,
        };

        console.log("Submitting product request: ", productRequest);

        const response = await axios.post('http://localhost:8080/products/', productRequest);

        console.log("Product added: ", response.data);
        this.setAlert('Product added successfully!', 'success', 'Success!');

        //Redirect after 2 secs
        setTimeout(() => {
          this.$router.push('/');
        }, 2000);
      } catch(error) {
        console.error('Error adding product: ', error);
        this.setAlert('Failed to add product.', 'danger', 'Error!');
      }
  },
  setAlert(message, type, title) {
      this.alertMessage = message;
      this.alertType = type;
      this.alertTitle = title;
    }
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
