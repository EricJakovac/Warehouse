<!-- component -->
<!-- full tailwind config using javascript -->
<!-- https://github.com/neurolinker/popice -->
 <template>

<div class="flex h-screen w-screen" style="height: 100%; overflow: hidden;">

  <div class="content">
    <div class="header" style="height: auto; display: inherit;">
        <div class="sidebar-header">
            <h2>Admin Dashboard</h2>
        </div>

        <!--Navbar-->
        <div class="navbar">
            <router-link to="/" style="display: flex; flex-direction: column;">
                <div class="nav_current">
                    <v-icon name="hi-home" class="icon" />
                    <h3>Home</h3>
                </div>
                <div class="line_current"></div>
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
      <div class="display">
        <div class="view">
          <div class="sidebar">
            <!--Pending Tab-->
            <div class="tabs">
              <div class="tab_current">
                <v-icon name="bi-circle-half" class="icon-side" style="color: yellow; width: 24px;"/>
                <h3 style="font-size: 19px; font-weight: 600;">Pending Orders</h3>
              </div>
              <div class="line_current_tab"></div>
            </div>
            <!--Processing Tab-->
            <div class="tabs">
              <div class="tab_link">
                <v-icon name="bi-circle-half" class="icon-side" style="color: blue;"/>
                <h3>Confirmed Orders</h3>
                </div>
                <div class="line_hover_tab"></div>
            </div>
            <!--Compketed Tab-->
            <div class="tabs">
              <div class="tab_link">
                <v-icon name="bi-circle-half" class="icon-side" style="color: green;"/>
                <h3>Delivered Orders</h3>
                </div>
                <div class="line_hover_tab"></div>
            </div>
            <!--Declined Tab-->
            <div class="tabs">
              <div class="tab_link">
                    <v-icon name="bi-circle-half" class="icon-side" style="color: red;"/>
                    <h3>Cancelled Orders</h3>
                </div>
                <div class="line_hover_tab"></div>
            </div>
          </div>
            
            <div class="p-4" style="width: 100%;">  
            <!--List-->
            <div class="transition-all duration-300" style="padding-top: 20px;">
                <div class="bg-white p-4 rounded-lg shadow-md border-l-4">
                <pending-orders-table />
                </div>
            </div>
            </div>

        </div>
      </div>
    
  </div>
</div>

</template>

<script>
      const sidebar = document.querySelector("aside");
      const maxSidebar = document.querySelector(".max")
      const miniSidebar = document.querySelector(".mini")
      const roundout = document.querySelector(".roundout")
      const maxToolbar = document.querySelector(".max-toolbar")
      const logo = document.querySelector('.logo')
      const content = document.querySelector('.content')
      const moon = document.querySelector(".moon")
      const sun = document.querySelector(".sun")

      function setDark(val){
          if(val === "dark"){
              document.documentElement.classList.add('dark')
              moon.classList.add("hidden")
              sun.classList.remove("hidden")
          }else{
              document.documentElement.classList.remove('dark')
              sun.classList.add("hidden")
              moon.classList.remove("hidden")
          }
      }

      function openNav() {
          if(sidebar.classList.contains('-translate-x-48')){
              // max sidebar 
              sidebar.classList.remove("-translate-x-48")
              sidebar.classList.add("translate-x-none")
              maxSidebar.classList.remove("hidden")
              maxSidebar.classList.add("flex")
              miniSidebar.classList.remove("flex")
              miniSidebar.classList.add("hidden")
              maxToolbar.classList.add("translate-x-0")
              maxToolbar.classList.remove("translate-x-24","scale-x-0")
              logo.classList.remove("ml-12")
              content.classList.remove("ml-12")
              content.classList.add("ml-12","md:ml-60")
          }else{
              // mini sidebar
              sidebar.classList.add("-translate-x-48")
              sidebar.classList.remove("translate-x-none")
              maxSidebar.classList.add("hidden")
              maxSidebar.classList.remove("flex")
              miniSidebar.classList.add("flex")
              miniSidebar.classList.remove("hidden")
              maxToolbar.classList.add("translate-x-24","scale-x-0")
              maxToolbar.classList.remove("translate-x-0")
              logo.classList.add('ml-12')
              content.classList.remove("ml-12","md:ml-60")
              content.classList.add("ml-12")

          }

      }

import axios from "axios";
import PendingOrdersTable from '../components/PendingOrdersTable.vue';
export default {
  components: {
    PendingOrdersTable,
  },
};
  </script>

<style scoped>
/* Tailwind setup */
@tailwind base;
@tailwind components;
@tailwind utilities;

/* Sidebar Styles */

.tabs {
  padding-bottom: 10px;
  display: flex;
  flex-direction: column;
}

.display {
  height: 100%;
}

.sidebar {
  height: 90%;
  min-width: 300px;
  padding: 20px;
  background-color: #fff;
  color: #0d1b2a;
  --tw-shadow: 0 4px 6px -1px rgba(0, 0, 0, .1), 0 2px 4px -2px rgba(0, 0, 0, .1);
    --tw-shadow-colored: 0 4px 6px -1px var(--tw-shadow-color), 0 2px 4px -2px var(--tw-shadow-color);
    box-shadow: var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000), var(--tw-shadow);
    margin: 20px 20px 0;
}

.view {
  height: 100%;
  background-color: #fff;
  display: flex;
  flex-direction: row;
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

.tab_current {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #0d1b2a;
  text-decoration: none;
  background-color: #fff;
  width: 100%;
}

.line_current {
  background-color: #7E99A3;
  height: 3px;
  width: 80px;
  align-self: center;
  border-radius: 150px;
}

.line_current_tab {
  background-color: #e7e7e7;
  height: 1.5px;
  width: 90%;
  align-self: center;
  border-radius: 150px;
  margin-top: 15px;
}

.nav_link {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #fff;
  text-decoration: none;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.tab_link {
  display: flex;
  align-items: center;
  padding: 15px 25px 10px 25px;
  color: #0d1b2a;
  text-decoration: none;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.nav_link:hover {
  background-color: #0d1b2a;
  color: white;
  width: 100%;
}

.tab_link:hover {
  background-color: #fff;
  color: #0d1b2a;
  width: 90%;
}

.nav_link:hover + .line_hover {
  opacity: 1;
  transform: scaleX(1);
}

.tab_link:hover + .line_hover_tab {
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

.line_hover_tab {
  display: block;
  background-color: #fff;
  height: 1px;
  opacity: 0;
  transform: scaleX(0);
  transform-origin: left;
  transition: opacity 0.3s, transform 0.3s;
  width: 90%;
  align-self: center;
  border-radius: 150px;
}

.icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
}

.icon-side {
  width: 14px;
  height: 24px;
  margin-right: 10px;
}

/* Content Styling */
.content {
  flex: 1;
  background-color: white;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
  height: 100vh;
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
