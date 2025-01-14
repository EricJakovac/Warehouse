<template>
  <div>
    <div class="mb-3" style="display: flex;">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search Rijeka data" />
    </div>
    <div class="content_wrapper">
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Name</th>
          <th scope="col">Info</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in filteredData" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.info }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',
      data: [
        { id: 1, name: 'John', info: 'Developer' },
        { id: 2, name: 'Jane', info: 'Designer' },
        { id: 3, name: 'Bob', info: 'Manager' },
      ],
    };
  },
  computed: {
    filteredData() {
      return this.data.filter((item) =>
        item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
  },
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