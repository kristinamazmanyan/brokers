PPCM.service('userService', function(pageConstants, $http){

    function post(url, body) {
        return $http.post('/manage/users/' + url, body)
    }
    function postTransform(url, body){
        var transform = function(body){
            return $.param(body);
        };
        return $http.post('/manage/users/'+url,body,{
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transform
        } );
    }

    function listUser() {
        return post('listUsers');
    }

    function listLA(id) {
        return post('listLAs?id='+id);
    }

    function getUser(id) {
        return post('get?id='+id);
    }

    function getProfile() {
        return post('get');
    }
    function addUser(user) {
        return post('add/', user);
    }

    function saveUser(user) {
        return post('save/', user);
    }
    function updateProfile(user) {
        return post('updateProfile/', user);
    }
    function changePassword(currentPassword,newPassword) {
        var data = {currentPassword:currentPassword,newPassword:newPassword};
        return postTransform('changePassword',data);
    }

    return {
        list :   listUser,
        get  :   getUser,
        add  :   addUser,
        save :   saveUser,
        getProfile: getProfile,
        update: updateProfile,
        change: changePassword

    }

})