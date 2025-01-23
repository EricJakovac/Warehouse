<template>
  <div>
    <h1>Edit Product</h1>
    <div v-if="loading">Loading product details...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <p><strong>Code:</strong> {{ product.code }}</p>
      <p><strong>Name:</strong> {{ product.name }}</p>
      <p><strong>Quantity:</strong> {{ product.quantity }}</p>
      <p><strong>Min Quantity:</strong> {{ product.min_quantity }}</p>
      <p><strong>Price:</strong> {{ product.price }}</p>
      <p><strong>Arrive Date:</strong> {{ product.arrive_date }}</p>
      <p><strong>Departure Date:</strong> {{ product.departure_date }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["productCode"],
  data() {
    return {
      product: null,
      loading: true,
      error: null,
    };
  },
  methods: {
    async fetchProductDetails() {
      try {
        const response = await axios.get(`http://localhost:8080/products/${this.productCode}`);
        this.product = {
          code: response.data.productCode,
          name: response.data.productName,
          quantity: response.data.productQuantity,
          min_quantity: response.data.productMinQuantity,
          price: response.data.productPrice,
          arrive_date: response.data.productArriveDate,
          departure_date: response.data.productDepartureDate,
        };
        this.loading = false;
      } catch (err) {
        console.error("Error fetching product details:", err);
        this.error = "Failed to fetch product details.";
        this.loading = false;
      }
    },
  },
  mounted() {
    this.fetchProductDetails();
  },
};
</script>
