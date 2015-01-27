function Device(data){
    var self = this;
    self.id = data.id;
    self.name = data.name;
    self.dateAdded = new Date(data.dateAdded);
}

function DevicesViewModel(data){
    var self = this;
    
    self.devices = ko.observableArray();
    if(!data)
        return;
    
    for(var i = 0; i < data.length; i++){
        self.devices.push(new Device(data[i]));
    }
}