function Session(data){
    var self = this;
    self.id = data.key;
    self.date = new Date(data.startTime);
    self.user = data.user;
    self.config = data.deviceConfig;
    self.browser = data.browser;
    self.location = data.location;
}

function SessionsViewModel(data){
    var self = this;
    
    self.sessions = ko.observableArray();
    if(!data)
        return;
    
    for(var i = 0; i < data.length; i++){
        self.sessions.push(new Session(data[i]));
    }
}