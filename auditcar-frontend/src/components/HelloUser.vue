<template>
  <div>
    <el-form>
    <el-button type="primary" @click="submitForm()" class="sign">登 录</el-button>
    </el-form>
  </div>

</template>

<script>
    import {getInfo} from "../api/login";

    export default {
        name: "HelloUser",
      data() {
        return {
          login_form: {
            login_email: '',
            login_pass: '',
          }
        };
      },
      methods: {
        submitForm() {
          getInfo();
      /*    this.$refs[login_form].validate(valid => {
            if (valid) {
              this.$store
                .dispatch("Login", this.login_form)
                .then(() => {
                  // this.loading = false;
                  this.$router.push({path: "/"});
                })
                .catch(() => {
                  this.loading = false;
                });
              // console.log("success")
            } else {
              // console.log("error submit!!");
              return false;
            }
          });*/
        },

      }
    }
</script>

<style scoped>

</style>
