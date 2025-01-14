<template>
  <div>
    <div class="mb-3">
      <input v-model="searchQuery" type="text" class="form-control" placeholder="Search Zagreb data" />
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
        { id: 3, name: 'John', info: 'Developer' },
        { id: 4, name: 'Jane', info: 'Designer' },
        { id: 5, name: 'Bob', info: 'Manager' },
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