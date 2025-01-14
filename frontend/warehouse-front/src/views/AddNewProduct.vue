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

      <!--Content-->
      <div class="view">

        <div class="content">
          <div class="content_wrapper">
        
        <div class="title">
            <h1>Form Example</h1>
        </div>
          
          <!--Form-->
          <div class="flex items-center justify-center p-12">
          <!-- Author: FormBold Team -->
          <!-- Learn More: https://formbold.com -->
            <div class="mx-auto w-full max-w-[550px]">
              <form action="https://formbold.com/s/FORM_ID" method="POST">
                <div class="-mx-3 flex flex-wrap">
                  <div class="w-full px-3 sm:w-1/2">
                    <div class="mb-5">
                      <label
                        for="fName"
                        class="mb-3 block text-base font-medium text-[#0d1b2a]"
                      >
                        First Name
                      </label>
                      <input
                        type="text"
                        name="fName"
                        id="fName"
                        placeholder="First Name"
                        class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                      />
                    </div>
                  </div>
                  <div class="w-full px-3 sm:w-1/2">
                    <div class="mb-5">
                      <label
                        for="lName"
                        class="mb-3 block text-base font-medium text-[#0d1b2a]"
                      >
                        Last Name
                      </label>
                      <input
                        type="text"
                        name="lName"
                        id="lName"
                        placeholder="Last Name"
                        class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                      />
                    </div>
                  </div>
                </div>
                <div class="mb-5">
                  <label
                    for="guest"
                    class="mb-3 block text-base font-medium text-[#0d1b2a]"
                  >
                    How many guest are you bringing?
                  </label>
                  <input
                    type="number"
                    name="guest"
                    id="guest"
                    placeholder="5"
                    min="0"
                    class="w-full appearance-none rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                  />
                </div>
  
                <div class="-mx-3 flex flex-wrap">
                  <div class="w-full px-3 sm:w-1/2">
                    <div class="mb-5">
                      <label
                        for="date"
                        class="mb-3 block text-base font-medium text-[#0d1b2a]"
                      >
                        Date
                      </label>
                      <input
                        type="date"
                        name="date"
                        id="date"
                        class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#0d1b2a] focus:shadow-md"
                      />
                    </div>
                  </div>
                  <div class="w-full px-3 sm:w-1/2">
                    <div class="mb-5">
                      <label
                        for="time"
                        class="mb-3 block text-base font-medium text-[#0d1b2a]"
                      >
                        Time
                      </label>
                      <input
                        type="time"
                        name="time"
                        id="time"
                        class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#0d1b2a] outline-none focus:border-[#6A64F1] focus:shadow-md"
                      />
                    </div>
                  </div>
                </div>
  
                <div class="mb-5">
                  <label class="mb-3 block text-base font-medium text-[#0d1b2a]">
                    Are you coming to the event?
                  </label>
                  <div class="flex items-center space-x-6">
                    <div class="flex items-center">
                      <input
                        type="radio"
                        name="radio1"
                        id="radioButton1"
                        class="h-5 w-5"
                      />
                      <label
                        for="radioButton1"
                        class="pl-3 text-base font-medium text-[#0d1b2a]"
                      >
                        Yes
                      </label>
                    </div>
                    <div class="flex items-center">
                      <input
                        type="radio"
                        name="radio1"
                        id="radioButton2"
                        class="h-5 w-5"
                      />
                      <label
                        for="radioButton2"
                        class="pl-3 text-base font-medium text-[#0d1b2a]"
                      >
                        No
                      </label>
                    </div>
                  </div>
                </div>
  
                <div>
                  <button class="middle none center mr-3 rounded-lg border border-[#1b263b] py-3 px-6 font-sans text-xs font-bold uppercase text-[#1b263b] transition-all hover:opacity-75 focus:ring focus:ring-gray-200 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
                    @click="$router.push({path: '/'})"
                    data-ripple-dark="true">
                    Cancel
                  </button>
                  <button
                    class="middle none center mr-3 rounded-lg bg-gradient-to-tr from-[#7E99A3] to-[#7E99A3] py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-500/20 transition-all hover:shadow-lg hover:shadow-gray-500/40 active:opacity-[0.85] disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none" 
                    @click="$router.push({path: '/'})"
                    data-ripple-light="true">
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
import WarehouseRijekaTable from "@/components/WarehouseRijekaTable.vue";
import WarehouseZagrebTable from "@/components/WarehouseZagrebTable.vue";
export default {
data() {
    return {
      activeTab: 0, // Default active tab
      tabs: [
        { title: "Rijeka" },
        { title: "Zagreb" }
      ]
    };
  },
  components: {
    WarehouseRijekaTable,
    WarehouseZagrebTable,
  },
  methods: {
    setActiveTab(index) {
      this.activeTab = index; // Update active tab index
    }
  }
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
