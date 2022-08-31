               const container = document.getElementById('input-cont');
               var maxInputAllowed = 5;
               var inputCount = 1;

               // Call addInput() function on button click
               function addInput(){
                   inputCount++; // Increment input count by one
                   if(inputCount>8){
                       alert('En esta version solo se puede crear 8 task...');
                       return;
                   }
                   let p = document.getElementById('name' + inputCount);
                                  p.removeAttribute("hidden");

               }

