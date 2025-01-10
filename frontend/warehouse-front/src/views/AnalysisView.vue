<template>
  <div class="flex h-screen w-screen">
    <div class="sidebar">
      <div class="sidebar-header">
        <h2>Admin Dashboard</h2>
      </div>
      <div class="nav">
        <router-link to="/" class=" nav_link">
          <v-icon name="hi-home" class="icon" />
          <h3>Home</h3>
        </router-link>
        <router-link to="/warehouse-management" class="nav_link">
          <v-icon name="bi-box-seam" class="icon" />
          <h3>Warehouse Management</h3>
        </router-link>
        <router-link to="/product-management" class="nav_link">
          <v-icon name="bi-basket3" class="icon" />
          <h3>Product Management</h3>
        </router-link>
        <router-link to="/orders" class="nav_link">
          <v-icon name="md-payments-outlined" class="icon" />
          <h3>Orders</h3>
        </router-link>
        <router-link to="/reports-and-analytics" class="nav_link">
          <v-icon name="io-analytics-sharp" class="icon" />
          <h3>Reports & Analytics</h3>
        </router-link>
        <router-link to="/predictive-analysis" class="nav_current">
          <v-icon name="co-magnifying-glass" class="icon" />
          <h3>Predictive Analysis</h3>
        </router-link>
        <router-link to="/settings" class="nav_link">
          <v-icon name="ri-settings-4-line" class="icon" />
          <h3>Settings</h3>
        </router-link>
      </div>
    </div>
    <div class="content">
      <div class="header">Menu</div>
      <div class="bootstrap-namespace">
        <!-- Bootstrap styles isolated here -->
        <button class="btn btn-primary">Bootstrap Button</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* Tailwind setup */
@tailwind base;
@tailwind components;
@tailwind utilities;

/* Sidebar Styles */
.sidebar {
  background-color: #1b263b;
  width: 300px;
  height: 100%;
  color: white;
  display: flex;
  flex-direction: column;
}

.sidebar-header {
  background-color: #0d1b2a;
  padding: 15px 25px;
  font-size: 18px;
  font-weight: 600;
}

.nav {
  flex: 1;
}

.nav_current {
  display: flex;
  align-items: center;
  padding: 15px 25px;
  color: white;
  text-decoration: none;
  background-color: #0d1b2a;
  width: 100%;
}

.nav_link {
  display: flex;
  align-items: center;
  padding: 15px 25px;
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

.header {
  background-color: #0d1b2a;
  padding: 10px 20px;
  color: white;
}

.bootstrap-namespace {
  padding: 20px;
}

/* Allow Bootstrap styles only inside bootstrap-namespace */
.bootstrap-namespace .btn {
  all: unset;
  display: inline-block;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  user-select: none;
  border: 1px solid transparent;
  border-radius: 0.375rem;
  color: #fff;
  background-color: #0d6efd;
  border-color: #0d6efd;
  transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
}

.bootstrap-namespace .btn:hover {
  background-color: #0b5ed7;
  border-color: #0a58ca;
}
</style>
