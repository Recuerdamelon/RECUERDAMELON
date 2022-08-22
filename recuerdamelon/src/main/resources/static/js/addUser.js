               const container = document.getElementById('input-cont');
               var maxInputAllowed = 5;
               var inputCount = 1;

               // Call addInput() function on button click
               function addInput(){
                   inputCount++; // Increment input count by one
                   if(inputCount>5){
                       alert('De 5 en 5 melón, estamos en la version Beta...');
                       return;
                   }
                   let p = document.getElementById('name' + inputCount);
                                  p.removeAttribute("hidden");

               }

