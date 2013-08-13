'use strict';

var mailTaskModule = angular.module('MailTaskModule',['ngResource']);

mailTaskModule.controller('MailTaskController', ['$scope','MailTaskService','$location', '$timeout',
    function($scope, MailTaskService, $location, $timeout) {

        $scope.mailTasks = MailTaskService.mailTask.query();

        MailTaskService.rememberedMailTask();

        $scope.errors = {};



        var observation;
        $scope.liveObservation = function() {
            observation = $timeout(function() {
                $scope.refreshMailTask(function() {
                    if($scope.activeMailTask.status == "RUNNING") {
                        $scope.liveObservation();
                    }
                    else {
                        $scope.refreshMailTasks();
                        $timeout.cancel(observation);
                    }
                });
            }, 3000);
        };



        $scope.activateMailTask = function(mailTask) {
            $location.search("id", mailTask.id);
        };

        $scope.activeMailTaskClass = function(mailTask) {
            if(angular.isDefined($scope.activeMailTask)) {
                if(mailTask.id == $scope.activeMailTask.id) {
                    return 'active';
                }
            }
        };

        $scope.$on('$routeUpdate', function() {
            $scope.initializeMailTask();
        });

        $scope.initializeMailTask = function() {
            var search = $location.search();
            if(angular.isDefined(search) && angular.isDefined(search.id)) {
                MailTaskService.mailTask.get(search.id,
                    function(mailTask) {
                        $scope.activeMailTask = mailTask;
                        updateUiState(mailTask);
                        if(mailTask.contentAvailable && mailTask.status != "RUNNING") {
                            $scope.updatePreview();
                        }
                        if(mailTask.status == "RUNNING") {
                            $scope.liveObservation();
                        }
                        MailTaskService.rememberMailTask(mailTask);
                    },
                    function() {
                        $location.search("id", null);
                        $scope.activeMailTask = undefined;
                    }
                );
            }
        };

        $scope.refreshMailTask = function(callback) {
            var search = $location.search();
            MailTaskService.mailTask.get(search.id,
                function(mailTask) {
                    $scope.activeMailTask = mailTask;
                    updateUiState(mailTask);
                    if(callback) {
                        callback();
                    }
                },
                function() {
                    $location.search("id", null);
                }
            );
        };
        function updateUiState(mailTask) {
            $scope.jobDoneOrRunning = angular.isDefined(mailTask) && (mailTask.status == 'DONE' || mailTask.status == 'RUNNING');
            $scope.noMailJobs = angular.isDefined(mailTask) && mailTask.mailJobs.length == 0;
            $scope.sendMailTaskButtonState = $scope.jobDoneOrRunning || ($scope.noMailJobs || !mailTask.contentAvailable);
            $scope.saveMailTaskButtonState = $scope.jobDoneOrRunning ||
                mailTask.topic == ''||
                mailTask.senderName == '' || mailTask.senderAddress == '';
            $scope.deleteMailReceiverButtonState = $scope.jobDoneOrRunning;
            $scope.uploadMailReceiverButtonState = $scope.jobDoneOrRunning;
            $scope.uploadMailTemplateButtonState = $scope.jobDoneOrRunning;
        }

        $scope.refreshMailTasks = function() {
            $scope.mailTasks = MailTaskService.mailTask.query();
        };


        $scope.saveMailTask = function() {
            MailTaskService.mailTask.save(
                $scope.activeMailTask.id, $scope.activeMailTask.topic, $scope.activeMailTask.comment,
                $scope.activeMailTask.senderName, $scope.activeMailTask.senderAddress,
            function(mailTask) {
                $scope.refreshMailTasks();
                $scope.activeMailTask = mailTask;
            });
        };
        $scope.createMailTask = function() {
            MailTaskService.mailTask.create(function(mailTask) {
                $scope.refreshMailTasks();
                $scope.activateMailTask(mailTask);
            });
        };
        $scope.deleteMailTask = function(mailTask) {
            MailTaskService.mailTask.delete(mailTask, function() {
                if(mailTask.id == $scope.activeMailTask.id) {
                    $scope.activeMailTask = undefined;
                    $location.search("id", null);
                }
            });
        };

        $scope.sendMailTask = function() {
            MailTaskService.mailTask.send($scope.activeMailTask.id, function() {
                $scope.refreshMailTask(function() {
                    $scope.refreshMailTasks();
                    $scope.liveObservation();
                });
            });
        };


        $scope.deleteAllMailReceiver = function() {
            MailTaskService.receiver.deleteAllReceiver($scope.activeMailTask, function(mailTask) {
                $scope.refreshMailTasks();
                updateUiState(mailTask);
                $scope.errors.importReceiver = [];
                $scope.activeMailTask = mailTask;
            });
        };
        $scope.deleteMailReceiver = function(mailJobId) {
            MailTaskService.receiver.deleteReceiver($scope.activeMailTask, mailJobId, function() {
                $scope.refreshMailTasks();
                $scope.refreshMailTask();
            });
        };


        $scope.testMailTask = function(mailAddress) {
            if($scope.activeMailTask.senderAddress == null || $scope.activeMailTask.senderName == null) {
                window.alert("Sender not defined correctly!");
            }
            else {
                MailTaskService.test.mail($scope.activeMailTask.id, mailAddress, function() {
                    window.alert("Testmail sent!");
                });
            }
        };
        $scope.updatePreview = function() {
            MailTaskService.test.preview($scope.activeMailTask.id, function(preview) {
                $scope.mailTaskPreview = preview;
            });
        };

        $scope.initializeMailTask();


        $('#receiverImport').on('submit', function(e) {
            $scope.importReceiverRunning = true;
            $scope.errors.importReceiver = [];
            e.preventDefault();
            $(this).ajaxSubmit({
                url : 'api/mailtask/'+ $scope.activeMailTask.id +'/mailjob',
                success: function() {
                    $scope.importReceiverRunning = false;
                    $scope.refreshMailTasks();
                    $scope.refreshMailTask();
                },
                error: function(response) {
                    $scope.errors.importReceiver = JSON.parse(response.responseText);
                }
            });
        });

        $('#templateImport').on('submit', function(e) {
            $scope.importTemplateRunning = true;
            $scope.errors.importTemplate = [];
            e.preventDefault();
            $(this).ajaxSubmit({
                url : 'api/mailtask/'+ $scope.activeMailTask.id +'/template',
                success: function() {
                    $scope.importTemplateRunning = false;
                    $scope.refreshMailTask();
                    $scope.updatePreview();
                },
                error: function(response) {
                    $scope.errors.importTemplate = JSON.parse(response.responseText);
                }
            });
        });
    }]
);



mailTaskModule.controller('MailReceiverCreationController', ['$scope', 'MailTaskService', function($scope, MailTaskService) {

    $scope.createMailReceiver = function(mailReceiver) {
        MailTaskService.receiver.createReceiver($scope.activeMailTask.id, mailReceiver,
            function() {
                $scope.hide();
                $scope.refreshMailTasks();
                $scope.refreshMailTask();
            },
            function(response) {
                $scope.errors.emailAddress = response;
            }
        );
    };

}]);



mailTaskModule.factory('MailTaskService',['$resource', '$http', '$location', 'MailTask','MailJob',
    function($resource, $http, $location, MailTask, MailJob) {
        function searchForCookie() {
            if($.cookie('mailtask')) {
                $location.search("id", $.cookie('mailtask'));
            }
        }

        function saveInCookie(mailTask) {
            $.cookie('mailtask', mailTask.id);
        }

        var test = {
            mail : function(mailTaskId, emailAddress, callback) {
                $http.post('/api/mailtask/' + mailTaskId + '/template/test', {emailAddress : emailAddress}).success(function(response) {
                    callback(response);
                });
            },
            preview : function(mailTaskId, callback) {
                $http.get('/api/mailtask/' + mailTaskId + '/template').success(function(response) {
                    callback(response);
                });
            }
        };

        var receiver = {
            deleteAllReceiver : function(mailTask, callback) {
                MailJob.delete({mailTaskId : mailTask.id}, function() {
                    mailTask.mailJobs = [];
                    callback(mailTask);
                });
            },
            deleteReceiver : function(mailTask, mailJobId, callback) {
                MailJob.delete({mailTaskId : mailTask.id, mailJobId : mailJobId}, function() {
                    callback(mailTask);
                });
            },
            createReceiver : function(mailTaskId, mailReceiver, success, failure) {
                var mailJob = new MailJob();
                mailJob.salutation = mailReceiver.salutation;
                mailJob.title = mailReceiver.title;
                mailJob.firstName = mailReceiver.firstName;
                mailJob.lastName = mailReceiver.lastName;
                mailJob.emailAddress = mailReceiver.emailAddress;

                mailJob.$save({mailTaskId: mailTaskId}, function(response) {
                    success(response);
                }, function(response) {
                    failure(response.data);
                });
            }
        };

        var mailTask = {
            query : function() {
                return MailTask.query();
            },
            get : function(mailTaskId, success, failure) {
                MailTask.get({mailTaskId : mailTaskId},
                    function(mailTask) {
                        success(mailTask);
                    },
                    function() {
                        failure();
                    }
                );
            },
            save : function(mailTaskId, topic, comment, senderName, senderAddress, callback) {
                MailTask.save(
                    {mailTaskId: mailTaskId},
                    {topic: topic, comment: comment, senderName: senderName, senderAddress: senderAddress},
                function(mailTask) {
                    callback(mailTask);
                });
            },
            create : function(callback) {
                MailTask.create({},function(mailTask) {
                    callback(mailTask);
                });
            },
            delete : function(mailTask, callback) {
                MailTask.get({mailTaskId : mailTask.id},function(mailTask) {
                    mailTask.$delete();
                    callback();
                });
            },
            send : function(mailTaskId, callback) {
                $http.post('/api/mailtask/' + mailTaskId + '/send').success(function(response) {
                    callback(response);
                });
            }
        };

        return {
            mailTask: mailTask,
            receiver: receiver,
            test : test,
            rememberedMailTask : function () { searchForCookie(); },
            rememberMailTask : function(mailTask) { saveInCookie(mailTask); }
        };

    }]
);


mailTaskModule.factory('MailTask', ['$resource', function($resource) {
    return $resource('api/mailtask/:mailTaskId', {mailTaskId : '@id'}, {
        create: {method: 'POST'},
        save: {method: 'PUT'}
    });
}]);

mailTaskModule.factory('MailJob', ['$resource', function($resource) {
    return $resource('api/mailtask/:mailTaskId/mailjob/:mailJobId', {mailTaskId : '@mailTaskId', mailJobId : '@mailJobId'});
}]);